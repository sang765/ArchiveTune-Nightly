#!/usr/bin/env python3
"""
Script to apply patches to Kotlin files based on the nightly build workflow modifications.
"""

import re
import os

def extract_sed_commands(workflow_path):
    """
    Extract sed commands from the GitHub Actions workflow file.

    Args:
        workflow_path (str): Path to the workflow YAML file

    Returns:
        dict: Dictionary mapping file paths to list of (pattern, replacement) tuples
    """
    sed_commands = {}

    try:
        with open(workflow_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # Find all sed -i commands
        sed_pattern = r"sed -i\s+['\"](.*?)['\"]\s+([^'\"\s]+)"
        matches = re.findall(sed_pattern, content)

        for replacement, file_path in matches:
            # Handle escaped quotes in sed commands
            replacement = replacement.replace("\\'", "'").replace('\\"', '"')

            # Handle different sed command types
            if replacement.startswith('s|') or replacement.startswith('s/'):
                # Substitution command: s/old/new/
                delimiter = replacement[1]
                parts = replacement[2:].split(delimiter)
                if len(parts) >= 2:
                    old_pattern = parts[0]
                    new_pattern = parts[1]
                    # Handle escaped delimiters
                    old_pattern = old_pattern.replace(f'\\{delimiter}', delimiter)
                    new_pattern = new_pattern.replace(f'\\{delimiter}', delimiter)

                    if file_path not in sed_commands:
                        sed_commands[file_path] = []
                    sed_commands[file_path].append(('substitute', old_pattern, new_pattern))
            elif '/a\\' in replacement:
                # Append command: /pattern/a\text
                parts = replacement.split('/a\\', 1)
                if len(parts) == 2:
                    pattern = parts[0]
                    text_to_append = parts[1].replace('\\n', '\n')  # Convert \n to actual newlines

                    if file_path not in sed_commands:
                        sed_commands[file_path] = []
                    sed_commands[file_path].append(('append', pattern, text_to_append))

    except FileNotFoundError:
        print(f"Error: Workflow file not found at {workflow_path}")
        return {}
    except Exception as e:
        print(f"Error reading workflow file: {e}")
        return {}

    return sed_commands

def apply_patches(patches_dir, sed_commands):
    """
    Apply the sed commands to files in the patches directory.

    Args:
        patches_dir (str): Directory containing the patch files
        sed_commands (dict): Dictionary of file paths to sed commands

    Returns:
        int: Number of successful patches applied
    """
    success_count = 0

    for file_path, commands in sed_commands.items():
        # Map workflow file path to patch file name
        patch_file = os.path.basename(file_path)
        patch_path = os.path.join(patches_dir, patch_file)

        if not os.path.exists(patch_path):
            print(f"Warning: Patch file not found: {patch_path}")
            continue

        try:
            with open(patch_path, 'r', encoding='utf-8') as f:
                content = f.read()

            original_content = content
            changes_made = 0

            for command_type, *args in commands:
                if command_type == 'substitute':
                    old_pattern, new_pattern = args
                    # Use regex for replacement, escaping special regex characters in old_pattern
                    escaped_old = re.escape(old_pattern)
                    content = re.sub(escaped_old, new_pattern, content)
                    if content != original_content:
                        changes_made += 1
                elif command_type == 'append':
                    pattern, text_to_append = args
                    # Find the line matching the pattern and append after it
                    lines = content.split('\n')
                    for i, line in enumerate(lines):
                        if pattern in line:
                            # Insert the text after this line
                            lines.insert(i + 1, text_to_append.rstrip('\n'))
                            content = '\n'.join(lines)
                            changes_made += 1
                            break

            # Apply additional hardcoded changes for Updater.kt
            if patch_file == 'Updater.kt':
                # Change APK filenames for nightly builds
                content = content.replace('"ArchiveTune.apk"', '"ArchiveTune_Nightly.apk"')
                content = content.replace('"app-${architecture}-release.apk"', '"app-${architecture}-nightly.apk"')
                changes_made += 1  # Count these as changes

            if changes_made > 0:
                with open(patch_path, 'w', encoding='utf-8') as f:
                    f.write(content)
                print(f"Applied {changes_made} patch(es) to {patch_file}")
                success_count += 1
            else:
                print(f"No changes needed for {patch_file}")

        except Exception as e:
            print(f"Error applying patches to {patch_file}: {e}")

    return success_count

def main():
    # Path to the workflow file relative to the project root
    workflow_path = os.path.join(os.path.dirname(__file__), '..', '.github', 'workflows', 'nightly-build.yml')

    # Directory containing the patch files
    patches_dir = os.path.join(os.path.dirname(__file__), '..', 'patches')

    if not os.path.exists(patches_dir):
        print(f"Error: Patches directory not found at {patches_dir}")
        return

    # Extract sed commands from workflow
    sed_commands = extract_sed_commands(workflow_path)

    if not sed_commands:
        print("No sed commands found in the workflow.")
        return

    print("Found sed commands for the following files:")
    for file_path in sed_commands:
        print(f"  - {file_path} ({len(sed_commands[file_path])} command(s))")

    print(f"\nApplying patches to files in {patches_dir}...")

    # Apply the patches
    success_count = apply_patches(patches_dir, sed_commands)

    print(f"\nSuccessfully applied patches to {success_count} file(s).")

if __name__ == "__main__":
    main()