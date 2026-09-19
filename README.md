# Quotes Generator

A simple, elegant Android app that serves up inspirational quotes with a modern, gradient-styled interface. Tap to get a fresh quote, copy it to your clipboard, or share it to any app in one tap.

> Built with Java and the Android SDK.

## Features

- **Fresh quotes on demand** — generate a new quote with a single tap
- **Copy to clipboard** — copy the current quote to paste anywhere
- **Share anywhere** — share the quote to WhatsApp, Instagram, notes, or any app via the Android share sheet
- **Modern UI** — clean gradient background with a styled quote card
- **Friendly feedback** — Toast messages confirm actions (copied, shared, etc.)

## Screenshots 


<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/96dbb09a-53b8-488c-a59c-124f3766a8ad" />


<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/e4ef142e-eadc-4ad9-a7f8-71599dca891a" />


<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/5c422adf-7b5f-47b9-9a8a-6dec32485d25" />



## Tech Stack

- **Language:** Java
- **UI:** Android XML layouts (AppCompat)
- **Build system:** Gradle (Kotlin DSL — `build.gradle.kts`)
- **Min IDE:** Android Studio

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (latest stable version recommended)
- Android SDK installed (Android Studio handles this on first launch)
- A physical Android device or an emulator (AVD)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/abhisheckpo/Quotes-Generator.git
   ```

2. Open the project in Android Studio:
   - **File → Open** and select the cloned `Quotes-Generator` folder.

3. Let Gradle sync finish (Android Studio does this automatically — wait for it to complete).

4. Click the green **Run** ▶ button and choose a device or emulator.

## Project Structure

```
Quotes-Generator/
├── app/
│   └── src/main/
│       ├── java/.../MainActivity.java   # App logic: fetch, copy, share
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml     # Main screen layout
│           │   └── modern_quote_box.xml  # Styled quote card
│           ├── drawable/
│           │   └── modern_gradient_bg.xml# Gradient background
│           └── values/
│               └── colors.xml            # Color palette
├── build.gradle.kts
└── README.md
```

## How It Works

- **`fetchQuote()`** loads a quote and displays it on screen.
- **`copyToClipboard()`** uses Android's `ClipboardManager` to copy the current quote.
- **`shareQuote()`** builds a `text/plain` share `Intent` and opens the system share chooser so you can send the quote to any installed app.

## Roadmap / Ideas

- [ ] Favorite / save quotes
- [ ] Quote categories (motivation, humor, wisdom, etc.)
- [ ] Dark mode toggle
- [ ] Widget for the home screen

## Author

**Abhisheck** — [@abhisheckpo](https://github.com/abhisheckpo)

## License

This project is currently unlicensed. If you'd like others to use it freely, consider adding an [MIT License](https://choosealicense.com/licenses/mit/).
