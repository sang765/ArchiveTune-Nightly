#!/bin/bash

cp temp/README.md ./README.md

RAW_CONTENT=$(curl -s https://raw.githubusercontent.com/koiverse/ArchiveTune/main/README.md)

awk -v content="$RAW_CONTENT" '

/Sync README\.md content from https:\/\/github\.com\/koiverse\/ArchiveTune raw\./ {

    print content

    next

}

{ print }

' ./README.md > temp_file && mv temp_file ./README.md