#!/bin/bash
# Script to prepare Telegram message

RELEASE_TAG="$1"
REPO="$2"
RELEASE_URL="https://github.com/$REPO/releases/tag/$RELEASE_TAG"

# Create APK links
printf '*Download:\n' > /tmp/apk_links.txt
printf '- Mobile 64-bit (arm64): https://github.com/%s/releases/download/%s/app-arm64-nightly.apk\n' "$REPO" "$RELEASE_TAG" >> /tmp/apk_links.txt
printf '- Mobile 32-bit (armeabi): https://github.com/%s/releases/download/%s/app-armeabi-nightly.apk\n' "$REPO" "$RELEASE_TAG" >> /tmp/apk_links.txt
printf '- Tablet 32-bit (x86): https://github.com/%s/releases/download/%s/app-x86-nightly.apk\n' "$REPO" "$RELEASE_TAG" >> /tmp/apk_links.txt
printf '- Tablet 64-bit (x86_64): https://github.com/%s/releases/download/%s/app-x86_64-nightly.apk\n' "$REPO" "$RELEASE_TAG" >> /tmp/apk_links.txt
printf '- Universal: https://github.com/%s/releases/download/%s/ArchiveTune-Nightly.apk\n' "$REPO" "$RELEASE_TAG" >> /tmp/apk_links.txt

# Create main message
cat > /tmp/message.txt << EOF
ArchiveTune Nightly $RELEASE_TAG Released

Check out changelog here: $RELEASE_URL

$(cat /tmp/apk_links.txt)
EOF

echo "release_url=$RELEASE_URL"
