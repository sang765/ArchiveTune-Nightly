#!/usr/bin/env python3
"""
Script to extract files that need to be modified from the nightly build workflow
and fetch their content from the original repository's dev branch.
"""

import re
import os
import urllib.request

def extract_modified_files(workflow_path):
    """
    Extract file paths that are modified in the GitHub Actions workflow file.

    Args:
        workflow_path (str): Path to the workflow YAML file

    Returns:
        list: Sorted list of unique file paths that are modified
    """
    modified_files = set()

    try:
        with open(workflow_path, 'r', encoding='utf-8') as f:
            for line in f:
                # Look for sed -i commands and extract file paths
                if 'sed -i' in line:
                    # Extract the file path at the end of the sed command
                    # Pattern: sed -i '...' file_path
                    match = re.search(r"sed -i\s+['\"](.*?)['\"]\s+([^'\"\s]+)", line)
                    if match:
                        file_path = match.group(2)
                        modified_files.add(file_path)
    except FileNotFoundError:
        print(f"Error: Workflow file not found at {workflow_path}")
        return []
    except Exception as e:
        print(f"Error reading workflow file: {e}")
        return []

    return sorted(modified_files)

def fetch_file_from_repo(file_path, output_dir):
    """
    Fetch a file from the original repository's dev branch.

    Args:
        file_path (str): Relative path to the file in the repo
        output_dir (str): Directory to save the fetched file

    Returns:
        bool: True if successful, False otherwise
    """
    url = f"https://raw.githubusercontent.com/koiverse/ArchiveTune/dev/{file_path}"
    try:
        with urllib.request.urlopen(url) as response:
            content = response.read().decode('utf-8')

        # Create output directory if it doesn't exist
        os.makedirs(output_dir, exist_ok=True)

        # Save the file
        output_path = os.path.join(output_dir, os.path.basename(file_path))
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write(content)

        print(f"Fetched and saved: {file_path} -> {output_path}")
        return True

    except urllib.error.URLError as e:
        print(f"Error fetching {file_path}: {e}")
        return False
    except Exception as e:
        print(f"Unexpected error fetching {file_path}: {e}")
        return False

def main():
    # Path to the workflow file relative to the project root
    workflow_path = os.path.join(os.path.dirname(__file__), '..', '.github', 'workflows', 'nightly-build.yml')

    # Extract modified files
    modified_files = extract_modified_files(workflow_path)

    if not modified_files:
        print("No modified files found in the workflow.")
        return

    print("Files that need to be modified in the nightly build:")
    for file in modified_files:
        print(f"  - {file}")

    # Directory to save fetched files
    output_dir = os.path.join(os.path.dirname(__file__), '..', 'patches')

    print(f"\nFetching files from original repository (dev branch) to {output_dir}...")

    success_count = 0
    for file_path in modified_files:
        if fetch_file_from_repo(file_path, output_dir):
            success_count += 1

    print(f"\nSuccessfully fetched {success_count}/{len(modified_files)} files.")

if __name__ == "__main__":
    main()