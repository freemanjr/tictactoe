package com.freemanjr.tictactoe;

public class TicTacToeGame {
    public enum Player {
        X, O
    }
    
    public enum GameState {
        PLAYING, X_WON, O_WON, DRAW
    }
    
    private final Player[][] board;
    private Player currentPlayer;
    private GameState gameState;
    
    public TicTacToeGame() {
        board = new Player[3][3];
        currentPlayer = Player.X;
        gameState = GameState.PLAYING;
    }
    
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        
        if (board[row][col] != null || gameState != GameState.PLAYING) {
            return false;
        }
        
        board[row][col] = currentPlayer;
        updateGameState();
        
        if (gameState == GameState.PLAYING) {
            currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
        }
        
        return true;
    }
    
    private void updateGameState() {
        if (checkWinner(Player.X)) {
            gameState = GameState.X_WON;
        } else if (checkWinner(Player.O)) {
            gameState = GameState.O_WON;
        } else if (isBoardFull()) {
            gameState = GameState.DRAW;
        }
    }
    
    private boolean checkWinner(Player player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Player getCell(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return null;
        }
        return board[row][col];
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
        currentPlayer = Player.X;
        gameState = GameState.PLAYING;
    }
}