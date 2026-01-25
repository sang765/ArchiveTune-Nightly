#!/usr/bin/env python3
import shutil
import requests

def fetch_content(url):
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.text
    except requests.exceptions.RequestException as e:
        print(f"Loading error {url}: {e}")
        return None

def main():
    shutil.copy('temp/README.md', 'README.md')
    
    sync_map = {
        "Sync README.md content from https://github.com/koiverse/ArchiveTune raw.": 
            "https://raw.githubusercontent.com/koiverse/ArchiveTune/main/README.md",
            
        "Sync CONTRIBUTING.md content from https://github.com/koiverse/ArchiveTune raw.": 
            "https://raw.githubusercontent.com/koiverse/ArchiveTune/dev/CONTRIBUTING.md"
    }

    with open('README.md', 'r', encoding='utf-8') as f:
        content = f.read()

    for placeholder, url in sync_map.items():
        print(f"Synchronizing from: {url}...")
        raw_data = fetch_content(url)
        
        if raw_data:
            content = content.replace(placeholder, raw_data)
        else:
            print(f"Placeholder ignored because data could not be loaded.")

    with open('README.md', 'w', encoding='utf-8') as f:
        f.write(content)
    
    print("Synchronization complete!")

if __name__ == "__main__":
    main()
