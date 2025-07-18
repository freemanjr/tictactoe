# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java-based Tic Tac Toe game with multiple interfaces:
- **JavaFX GUI**: Main graphical interface (`TicTacToeApp.java`)
- **Console**: Interactive command-line version (`TicTacToeConsole.java`)
- **Demo**: Sample gameplay demonstration (`TicTacToeDemo.java`)
- **Core Logic**: Game engine and rules (`TicTacToeGame.java`)

## Build System & Commands

This project uses Gradle with Java 21 and JavaFX 21.0.2.

### Essential Commands
```bash
# Build the project
./gradlew build

# Run tests with detailed output
./gradlew test

# Run the JavaFX GUI application
./gradlew run

# Build JAR file
./gradlew jar

# Clean build artifacts
./gradlew clean
```

### Testing
- Uses JUnit Jupiter 5.10.1
- Comprehensive test suite in `TicTacToeGameTest.java`
- Tests cover game logic, win conditions, player alternation, and edge cases
- Run single test: `./gradlew test --tests "TicTacToeGameTest.testSpecificMethod"`

## Architecture

### Core Components
- **TicTacToeGame**: Game state management, move validation, win detection
  - Enums: `Player` (X, O), `GameState` (PLAYING, X_WON, O_WON, DRAW)
  - 3x3 board representation using `Player[][]`
  - Alternating player turns with move validation

- **TicTacToeApp**: JavaFX application with 400x450px window
  - 3x3 grid of 100x100px buttons
  - Real-time status display and game over dialogs
  - New game functionality

- **TicTacToeConsole**: Command-line interface
  - Input format: "row,col" (0-2)
  - ASCII board display with play-again option

### Package Structure
```
com.freemanjr.tictactoe/
├── TicTacToeGame.java      # Core game logic
├── TicTacToeApp.java       # JavaFX GUI (main class)
├── TicTacToeConsole.java   # Console interface
└── TicTacToeDemo.java      # Demo/example usage
```

### Key Design Patterns
- MVC separation: Game logic separate from UI
- Immutable game state queries via getter methods
- Defensive programming with bounds checking and null validation