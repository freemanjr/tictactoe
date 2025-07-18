# Tic Tac Toe

A Java-based Tic Tac Toe game with multiple interfaces including JavaFX GUI, console, and demo modes.

## Features

- **JavaFX GUI**: Interactive graphical interface with 400x450px window
- **Console Interface**: Command-line version with ASCII board display  
- **Demo Mode**: Sample gameplay demonstration
- **Core Game Engine**: Robust game logic with win detection and validation

## Requirements

- Java 21
- JavaFX 21.0.2
- Gradle (wrapper included)

## Quick Start

### Run the GUI Application
```bash
./gradlew run
```

### Build the Project
```bash
./gradlew build
```

### Run Tests
```bash
./gradlew test
```

### Other Commands
```bash
# Build JAR file
./gradlew jar

# Clean build artifacts
./gradlew clean

# Run specific test
./gradlew test --tests "TicTacToeGameTest.testSpecificMethod"
```

## Project Structure

```
com.freemanjr.tictactoe/
├── TicTacToeGame.java      # Core game logic and state management
├── TicTacToeApp.java       # JavaFX GUI application (main class)
├── TicTacToeConsole.java   # Interactive command-line interface
└── TicTacToeDemo.java      # Sample gameplay demonstration
```

## Architecture

### Core Components

- **TicTacToeGame**: Manages game state, move validation, and win detection
  - Enums: `Player` (X, O), `GameState` (PLAYING, X_WON, O_WON, DRAW)
  - 3x3 board using `Player[][]` representation
  - Alternating player turns with comprehensive validation

- **TicTacToeApp**: JavaFX application
  - 3x3 grid of 100x100px interactive buttons
  - Real-time status updates and game over dialogs
  - New game functionality

- **TicTacToeConsole**: Command-line interface
  - Input format: "row,col" (0-2 coordinates)
  - ASCII board visualization
  - Play-again option

### Testing

- Uses JUnit Jupiter 5.10.1
- Comprehensive test coverage in `TicTacToeGameTest.java`
- Tests include game logic, win conditions, player alternation, and edge cases

## How to Play

1. **GUI Mode**: Click on empty squares to place your mark (X or O)
2. **Console Mode**: Enter coordinates as "row,col" (e.g., "1,1" for center)
3. **Goal**: Get three of your marks in a row (horizontally, vertically, or diagonally)

## License

This project is open source.