<div align="center">
<img width="150" height="150" src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/icon.png" alt="Archivetune">

# ArchiveTune Nightly

[![GitHub stars](https://img.shields.io/github/stars/koiverse/ArchiveTune?style=for-the-badge&logo=github)](https://github.com/koiverse/ArchiveTune)
[![GitHub forks](https://img.shields.io/github/forks/koiverse/ArchiveTune?style=for-the-badge&logo=github)](https://github.com/koiverse/ArchiveTune/forks)
[![GitHub issues](https://img.shields.io/github/issues/koiverse/ArchiveTune?style=for-the-badge&logo=github)](https://github.com/koiverse/ArchiveTune/issues)  
[![GitHub pull requests](https://img.shields.io/github/issues-pr/koiverse/ArchiveTune?style=for-the-badge&logo=github)](https://github.com/koiverse/ArchiveTune/pulls)
[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/sang765/ArchiveTune-Nightly/nightly-build.yml?branch=main&style=for-the-badge&logo=github&label=Status)](https://github.com/sang765/ArchiveTune-Nightly/actions/workflows/nightly-build.yml)

</div>

> [!WARNING]
> - This is **unofficial** Nightly build of AchieveTune. If you wanna download official ArchiveTune, please check out this [repository](https://github.com/koiverse/ArchiveTune).

This repository contains the nightly build setup for ArchiveTune, an advanced YouTube Music client for Android.

<div align="center">
  
## About ArchiveTune

</div>

ArchiveTune is an Android application that provides an enhanced YouTube Music experience with features like:
- Offline music downloading
- Seamless playback without ads
- Synchronized lyrics (LRC, TTML formats)
- Audio effects and normalization
- Android Auto support
- Scrobbling to LastFM and ListenBrainz
- Material3 design

Built with Kotlin, Jetpack Compose, and ExoPlayer.

<div align="center">
<h2>ArchiveTune Screenshots</h2>

  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_1.jpg" width="30%" />
  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_2.jpg" width="30%" />
  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_3.jpg" width="30%" />
  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_4.jpg" width="30%" />
  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_5.jpg" width="30%" />
  <img src="https://github.com/koiverse/ArchiveTune/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/screenshot_6.jpg" width="30%" />
</p>
</div>

<div align="center">
<h2>Translations</h2>

[![Status penerjemahan](https://translate.codeberg.org/widget/archivetune/287x66-grey.png)](https://translate.codeberg.org/engage/archivetune/)

We use Codeberg to translate ArchiveTune. For more details or to get started, visit our [ArchiveTune page](https://translate.codeberg.org/projects/archivetune/).

<a href="https://translate.codeberg.org/projects/archivetune/">
<img src="https://translate.codeberg.org/widget/archivetune/horizontal-blue.svg" alt="Translation status" />
</a>

Thank you very much for helping to make ArchiveTune accessible to many people worldwide.
</div>

<div align="center">
<h2>Credits & Acknowledgments</h2>

ArchiveTune is a derivative work based on [**Metrolist**](https://github.com/mostafaalagamy/Metrolist) by **Mostafa Alagamy**.

Additional acknowledgments:
- [**Kizzy**](https://github.com/dead8309/Kizzy) – for the Discord Rich Presence implementation and inspiration.
- The open-source community for tools, libraries, and APIs that make this project possible.
</div>

<div align="center">
<h2>Awesome Contributors</h2>
<a href="https://github.com/koiverse/ArchiveTune/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=koiverse/ArchiveTune" />
</a>
</div>

<div align="center">
<h2>Disclaimer</h2>
</div>

This project and its contents are not affiliated with, funded, authorized, endorsed by, or in any way associated with YouTube, Google LLC, ArchiveTune Group LLC or any of its affiliates and subsidiaries.

Any trademark, service mark, trade name, or other intellectual property rights used in this project are owned by the respective owners.


<div align="center">
Made with ❤️ by <strong>Koiverse</strong>
</div>
<div align="center">
Give a ⭐ if you like it, there's no harm in giving a ⭐ as support, right?
</div>

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
