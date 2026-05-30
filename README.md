# Thermo-Flow 🌡️

A simple and intuitive Android temperature conversion application built with Kotlin.

## Overview

Thermo-Flow allows users to quickly convert temperatures between Celsius, Fahrenheit, and Kelvin. The app features a clean, user-friendly interface with real-time formula display, showing both the conversion result and the step-by-step calculation used.

## Features

- **Three Temperature Units**: Convert between Celsius (°C), Fahrenheit (°F), and Kelvin (K)
- **Dual Conversion Display**: Automatically converts the input temperature to the two other units simultaneously
- **Formula Visualization**: See the exact formula used for each conversion, with your input value substituted in
- **Interactive Unit Selection**: Tap unit buttons (Celsius, Fahrenheit, Kelvin) to switch the input mode
- **Precision Formatting**: Results displayed to 2 decimal places for accuracy

## Screenshots

*Main Screen → Conversion Screen*

The app consists of two activities:
1. **MainActivity** — Welcome screen with a "Let's Convert" button
2. **MainActivity2** — The conversion interface with unit toggles, input field, and result display

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Kotlin | Primary programming language |
| Android SDK | Native Android development |
| Material Design Components | TextInputEditText for modern input styling |
| View Binding | UI element interaction |
| DecimalFormat | Number formatting for clean formula display |

## How It Works

1. Select your input unit (Celsius, Fahrenheit, or Kelvin)
2. Enter a temperature value
3. Tap **Convert**
4. View the converted values and the formulas used:

| From | To | Formula |
|------|-----|---------|
| Celsius | Fahrenheit | `F = (C × 9/5) + 32` |
| Celsius | Kelvin | `K = C + 273.15` |
| Fahrenheit | Celsius | `C = (F − 32) × 5/9` |
| Fahrenheit | Kelvin | `K = (F − 32) × 5/9 + 273.15` |
| Kelvin | Celsius | `C = K − 273.15` |
| Kelvin | Fahrenheit | `F = (K − 273.15) × 9/5 + 32` |

## Project Structure

```
com.example.tempcon/
├── MainActivity.kt      # Entry point / Welcome screen
└── MainActivity2.kt     # Conversion logic & UI
```

## Getting Started

### Prerequisites

- Android Studio (latest stable version recommended)
- Android SDK 21+ (Android 5.0 Lollipop or higher)
- Kotlin plugin enabled

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/orlanznaluz/Thermo-Flow.git
   ```

2. Open the project in Android Studio

3. Sync Gradle and build the project

4. Run on an emulator or physical device

## UI Design

- **Color Palette**: Deep indigo (`#161D6F`) for selected states, purple (`#664FA3`) for default buttons
- **Layout**: Clean card-based layout with clear visual hierarchy
- **Edge-to-Edge Support**: Utilizes `WindowInsetsCompat` for modern Android edge-to-edge display

## Future Improvements

- [ ] Add history of recent conversions
- [ ] Support for additional temperature scales (Rankine, Réaumur)
- [ ] Dark mode support
- [ ] Copy-to-clipboard functionality for results
- [ ] Haptic feedback on button presses

## License

This project is open source. Feel free to fork and contribute!

## Author

**orlanznaluz** — [GitHub Profile](https://github.com/orlanznaluz)

---

<p align="center">Made with ☕ and Kotlin</p>
