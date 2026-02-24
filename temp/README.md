<div align="center">
<img width="150" height="150" src="https://raw.githubusercontent.com/sang765/ArchiveTune-Nightly/main/images/logo.gif" alt="Archivetune">

# <img width="30" height="30" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune"> ArchiveTune Nightly <img width="30" height="30" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune">
**CI build for Nightly version of [ArchiveTune](https://github.com/koiverse/ArchiveTune). Redefining the YouTube Music Experience on Android.**  
🕛 Runs at <b><code>0:00 AM</code></b> every day, **UTC 0** time zone 🕛

<sub>Don't forget to leave a ⭐ for this repository if you visit. Thank you so much ♥️</sub>

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?branch=main&style=for-the-badge&logo=github&label=Status)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml)
[![GitHub Stars](https://img.shields.io/github/stars/sang765/ArchiveTune-Nightly?style=for-the-badge&color=6366f1&labelColor=1e1e2e&logo=github)](https://github.com/sang765/ArchiveTune-Nightly)
[![GitHub Downloads (all assets, latest release)](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/total?style=for-the-badge&logo=downdetector&label=DOWNLOAD%20COUNT&color=ff00ff)](https://github.com/sang765/ArchiveTune-Nightly/releases)
[![GitHub Release](https://img.shields.io/github/v/release/sang765/ArchiveTune-Nightly?display_name=release&style=for-the-badge&logo=Github&color=000fa0)](https://github.com/sang765/ArchiveTune-Nightly/releases/latest)
[![GitHub License](https://img.shields.io/github/license/sang765/ArchiveTune-Nightly?style=for-the-badge&logo=gplv3&logoColor=%23BD0000&color=%23BD0000)](./LICENSE)  
<a href="https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml">
  <img src="images/badges/last-nightly-build.svg" alt="Last Nightly Build" style="height: 28px;">
</a>

</div>

<h4 align="center">Download</h4>

<div align="center">

| Stable | Nightly |
|----------|---------|
| <div align="center"> [![Stable build](https://img.shields.io/github/actions/workflow/status/koiverse/ArchiveTune/release.yml?labelColor=27303D&label=Stable&labelColor=06599d&color=043b69)](https://github.com/koiverse/ArchiveTune/actions/workflows/release.yml) <br> [![GitHub downloads](https://img.shields.io/github/downloads/koiverse/ArchiveTune/latest/total?label=Latest%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/koiverse/ArchiveTune/releases/latest) [![GitHub downloads](https://img.shields.io/github/downloads/koiverse/ArchiveTune/total?label=Total%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/koiverse/ArchiveTune/releases) </div> | <div align="center"> [![Nightly build](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?labelColor=27303D&label=Nightly&labelColor=2c2c47&color=1c1c39)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml) <br> [![GitHub downloads](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/latest/total?label=Latest%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/sang765/ArchiveTune-Nightly/releases/latest) [![GitHub downloads](https://img.shields.io/github/downloads/sang765/ArchiveTune-Nightly/total?label=Total%20Downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://github.com/sang765/ArchiveTune-Nightly/releases) </div> |

</div>

> [!WARNING]
> - This is **unofficial** Nightly build of AchieveTune. If you wanna download official ArchiveTune, please check out this [repository](https://github.com/koiverse/ArchiveTune).
> - We **DO NOT ACCEPT** any issues, bug reports, or new ideas on "this repository". If you wanna create an issue or bug report, please create it [here](https://github.com/koiverse/ArchiveTune/issues/new/choose).

This repository contains the nightly build setup for ArchiveTune, an advanced YouTube Music client for Android. Read bellow to read more about this app.

<div align="center">

## ℹ️ [ArchiveTune](https://github.com/koiverse/ArchiveTune) [README](https://raw.githubusercontent.com/koiverse/ArchiveTune/main/README.md) ℹ️

</div>

Sync README.md content from https://github.com/koiverse/ArchiveTune raw.

<div align="center">

## 👥 [ArchiveTune](https://github.com/koiverse/ArchiveTune) [CONTRIBUTING](https://raw.githubusercontent.com/koiverse/ArchiveTune/dev/CONTRIBUTING.md) 👥

</div>

Sync CONTRIBUTING.md content from https://github.com/koiverse/ArchiveTune raw.

<div align="center">

## 🛠️ Development Environment 🛠️

</div>

This project is set up for development in Firebase Studio (IDX) with Nix environment configuration.

<div align="center">

### 💬 Requirements 💬

</div>

**Repository secrets:**  
- `SIGNING_KEY_BASE64`: Your key convent to base64 strings. You can converter your key into base64 with this command.  
```bash
openssl base64 < YOUR_SIGN_KEY_NAME_HERE.jks | tr -d '\n' | tee YOUR_SIGN_KEY_NAME_HERE.jks.base64
```
- `KEYSTORE_PASSWORD`: Password for your keystore
- `KEY_ALIAS`: Alias for your keystore
- `KEY_PASSWORD`: Password for your key
- `LASTFM_API_KEY` and `LASTFM_SECRET`: last.fm API key and Secret key for "Integrated last.fm" allow you login last.fm inside app. Checkout [API document](https://www.last.fm/api).
- `PAT_TOKEN`: your "[Personal Access Token](https://github.com/settings/personal-access-tokens)" for follow permission
```diff
+ "Actions" for trigger/running workflow.
+ "Contents" for commit, push and create release.
+ "Workflows" for cancel workflows if build failed (optional).
```


- Firebase Studio (IDX) workspace or VSCode
- [openssl](https://github.com/openssl/openssl/blob/master/INSTALL.md#installation-steps-in-detail) for coventer your key to base64
- keytool: You can use Java to have keytool command.
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
