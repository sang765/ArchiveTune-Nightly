<div align="center">
<img width="150" height="150" src="https://raw.githubusercontent.com/sang765/ArchiveTune-Nightly/main/images/logo.gif" alt="Archivetune">

# <img width="30" height="30" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune"> ArchiveTune Nightly <img width="30" height="30" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune">

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?branch=main&style=for-the-badge&logo=github&label=Status)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml)
[![GitHub Downloads (all assets, latest release)](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/latest/total?style=for-the-badge&logo=Github&label=NIGHTLY%20DOWNLOAD&color=ff00ff)](https://github.com/sang765/ArchiveTune-Nightly/releases)
<img src="images/badges/last-nightly-build.svg" alt="Last Nightly Build" style="height: 28px;">
</div>

> [!WARNING]
> - This is **unofficial** Nightly build of AchieveTune. If you wanna download official ArchiveTune, please check out this [repository](https://github.com/koiverse/ArchiveTune).

| Stable | Nightly |
|----------|---------|
| <div align="center"> [![GitHub downloads](https://img.shields.io/github/downloads/koiverse/ArchiveTune/latest/total?label=Latest%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/koiverse/ArchiveTune/releases/latest) [![GitHub downloads](https://img.shields.io/github/downloads/koiverse/ArchiveTune/total?label=Total%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/koiverse/ArchiveTune/releases) [![Stable build](https://img.shields.io/github/actions/workflow/status/koiverse/ArchiveTune/release.yml?labelColor=27303D&label=Stable&labelColor=06599d&color=043b69)](https://github.com/koiverse/ArchiveTune/actions/workflows/release.yml) | <div align="center"> [![GitHub downloads](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/latest/total?label=Latest%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/sang765/ArchiveTune-Nightly/releases/latest) [![GitHub downloads](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/total?label=Total%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/sang765/ArchiveTune-Nightly/releases) [![Preview build](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?labelColor=27303D&label=Nightly&labelColor=2c2c47&color=1c1c39)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml) |

This repository contains the nightly build setup for ArchiveTune, an advanced YouTube Music client for Android. Click bellow to read more about this app.

<details>
<summary>Click here to read more about ArchiveTune</summary>

Sync README.md content from https://github.com/koiverse/ArchiveTune raw.

</details>

<div align="center">

## 🛠️ Development Environment 🛠️

</div>

This project is set up for development in Firebase Studio (IDX) with Nix environment configuration.

<div align="center">

### 💬 Requirements 💬

</div>

- Firebase Studio (IDX) workspace
- Android SDK (configured via Nix)

<div align="center">

### ✈️ Setup ✈️

</div>

1. Open this repository in Firebase Studio
2. The `.idx/dev.nix` file defines the development environment with Android tools
3. Customize the environment as needed for your development workflow

<div align="center">

### ⚒️ Build ⚒️

</div>

- ~~Use `./gradlew` commands in the `ArchiveTune/` directory~~
- For nightly builds, see `.github/workflows/nightly-build.yml`

<div align="center">

### 🔗 Links 🔗

</div>

- Latest dev build in **ArchiveTune** repository:  
  [![Latest Dev Branch Workflow Build](https://img.shields.io/github/actions/workflow/status/koiverse/ArchiveTune/build.yml?branch=dev&style=for-the-badge&logo=githubactions&logoColor=ffffff&label=Last%20DEV%20Build&labelColor=1e1e2e&color=6366f1)](https://github.com/koiverse/ArchiveTune/actions/workflows/build.yml?query=branch%3Adev)
- [ArchiveTune Repository](https://github.com/koiverse/ArchiveTune)
- [Firebase Studio Documentation](https://developers.google.com/idx/guides/customize-idx-env)
