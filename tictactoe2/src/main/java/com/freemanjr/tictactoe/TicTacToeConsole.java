package com.freemanjr.tictactoe;

import java.util.Scanner;

public class TicTacToeConsole {
    private TicTacToeGame game;
    private Scanner scanner;
    
    public TicTacToeConsole() {
        game = new TicTacToeGame();
        scanner = new Scanner(System.in);
    }
    
    public void play() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Enter moves as row,col (0-2)");
        System.out.println();
        
        while (game.getGameState() == TicTacToeGame.GameState.PLAYING) {
            displayBoard();
            System.out.println("Current player: " + game.getCurrentPlayer());
            System.out.print("Enter your move (row,col): ");
            
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(",");
                
                if (parts.length != 2) {
                    System.out.println("Invalid format. Use row,col (e.g., 1,2)");
                    continue;
                }
                
                int row = Integer.parseInt(parts[0].trim());
                int col = Integer.parseInt(parts[1].trim());
                
                if (game.makeMove(row, col)) {
                    System.out.println();
                } else {
                    System.out.println("Invalid move! Try again.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers.");
            }
        }
        
        displayBoard();
        displayGameResult();
        
        System.out.print("Play again? (y/n): ");
        String playAgain = scanner.nextLine().trim().toLowerCase();
        if (playAgain.equals("y") || playAgain.equals("yes")) {
            game.reset();
            play();
        }
    }
    
    private void displayBoard() {
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
    
    private void displayGameResult() {
        switch (game.getGameState()) {
            case X_WON -> System.out.println("🎉 Player X wins!");
            case O_WON -> System.out.println("🎉 Player O wins!");
            case DRAW -> System.out.println("🤝 It's a draw!");
            default -> System.out.println("Game over.");
        }
    }
    
    public static void main(String[] args) {
        new TicTacToeConsole().play();
    }
}