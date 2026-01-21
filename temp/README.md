<div align="center">
<img width="150" height="150" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune">

# ArchiveTune Nightly

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?branch=main&style=for-the-badge&logo=github&label=Status)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml)   
[![GitHub Downloads (all assets, latest release)](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/latest/total?style=for-the-badge&logo=Github&label=NIGHTLY%20DOWNLOAD&color=ff00ff)](https://github.com/sang765/ArchiveTune-Nightly/releases)
</div>

> [!WARNING]
> - This is **unofficial** Nightly build of AchieveTune. If you wanna download official ArchiveTune, please check out this [repository](https://github.com/koiverse/ArchiveTune).

This repository contains the nightly build setup for ArchiveTune, an advanced YouTube Music client for Android. Click bellow to read more about this app.

<details>
<summary>Click here to read more about ArchiveTune</summary>

Sync README.md content from https://github.com/koiverse/ArchiveTune raw.

</details>

## Development Environment

This project is set up for development in Firebase Studio (IDX) with Nix environment configuration.

### Prerequisites
- Firebase Studio (IDX) workspace
- Android SDK (configured via Nix)

### Setup
1. Open this repository in Firebase Studio
2. The `.idx/dev.nix` file defines the development environment with Android tools
3. Customize the environment as needed for your development workflow

### Building
- Use `./gradlew` commands in the `ArchiveTune/` directory
- For nightly builds, see `.github/workflows/nightly-build.yml`

### Contributing
- Fork the repository
- Make changes in the `ArchiveTune/` directory
- Test builds using the provided Gradle wrapper

## Links
- [ArchiveTune Main Repository](https://github.com/koiverse/ArchiveTune)
- [Firebase Studio Documentation](https://developers.google.com/idx/guides/customize-idx-env)
