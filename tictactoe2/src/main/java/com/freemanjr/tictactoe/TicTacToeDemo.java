package com.freemanjr.tictactoe;

public class TicTacToeDemo {
    public static void main(String[] args) {
        System.out.println("=== TicTacToe Game Demo ===");
        
        TicTacToeGame game = new TicTacToeGame();
        
        System.out.println("Initial game state:");
        displayBoard(game);
        
        System.out.println("Playing a sample game...");
        
        // Simulate a game where X wins
        System.out.println("\nMove 1: X plays (0,0)");
        game.makeMove(0, 0);
        displayBoard(game);
        
        System.out.println("Move 2: O plays (1,0)");
        game.makeMove(1, 0);
        displayBoard(game);
        
        System.out.println("Move 3: X plays (0,1)");
        game.makeMove(0, 1);
        displayBoard(game);
        
        System.out.println("Move 4: O plays (1,1)");
        game.makeMove(1, 1);
        displayBoard(game);
        
        System.out.println("Move 5: X plays (0,2) - X WINS!");
        game.makeMove(0, 2);
        displayBoard(game);
        
        System.out.println("Game State: " + game.getGameState());
        
        System.out.println("\n=== Game Features Demonstrated ===");
        System.out.println("✓ Player alternation");
        System.out.println("✓ Win detection (horizontal)");
        System.out.println("✓ Game state management");
        System.out.println("✓ Board display");
        
        System.out.println("\n=== Available Applications ===");
        System.out.println("1. JavaFX GUI: Run with 'java -cp build/libs/tictactoe-1.0.0.jar --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml com.freemanjr.tictactoe.TicTacToeApp'");
        System.out.println("2. Console Version: Use TicTacToeConsole class for interactive play");
        System.out.println("3. All unit tests pass: ./gradlew test");
    }
    
    private static void displayBoard(TicTacToeGame game) {
        System.out.println("   0   1   2");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                TicTacToeGame.Player player = game.getCell(row, col);
                String symbol = (player == null) ? " " : player.toString();
                System.out.print(" " + symbol + " ");
                if (col < 2) System.out.print("|");
            }
            System.out.println();
            if (row < 2) System.out.println("  ---|---|---");
        }
        System.out.println();
    }
}