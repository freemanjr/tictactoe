package com.freemanjr.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {
    private TicTacToeGame game;
    
    @BeforeEach
    void setUp() {
        game = new TicTacToeGame();
    }
    
    @Test
    void testInitialState() {
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        assertEquals(TicTacToeGame.GameState.PLAYING, game.getGameState());
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertNull(game.getCell(row, col));
            }
        }
    }
    
    @Test
    void testValidMove() {
        assertTrue(game.makeMove(1, 1));
        assertEquals(TicTacToeGame.Player.X, game.getCell(1, 1));
        assertEquals(TicTacToeGame.Player.O, game.getCurrentPlayer());
    }
    
    @Test
    void testInvalidMoveOutOfBounds() {
        assertFalse(game.makeMove(-1, 0));
        assertFalse(game.makeMove(0, -1));
        assertFalse(game.makeMove(3, 0));
        assertFalse(game.makeMove(0, 3));
    }
    
    @Test
    void testInvalidMoveOccupiedCell() {
        game.makeMove(1, 1);
        assertFalse(game.makeMove(1, 1));
        assertEquals(TicTacToeGame.Player.X, game.getCell(1, 1));
    }
    
    @Test
    void testPlayerAlternation() {
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        
        game.makeMove(0, 0);
        assertEquals(TicTacToeGame.Player.O, game.getCurrentPlayer());
        
        game.makeMove(0, 1);
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        
        game.makeMove(0, 2);
        assertEquals(TicTacToeGame.Player.O, game.getCurrentPlayer());
    }
    
    @Test
    void testHorizontalWin() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins
        
        assertEquals(TicTacToeGame.GameState.X_WON, game.getGameState());
    }
    
    @Test
    void testVerticalWin() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 0); // X wins
        
        assertEquals(TicTacToeGame.GameState.X_WON, game.getGameState());
    }
    
    @Test
    void testDiagonalWin() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 2); // X wins
        
        assertEquals(TicTacToeGame.GameState.X_WON, game.getGameState());
    }
    
    @Test
    void testAntiDiagonalWin() {
        game.makeMove(0, 2); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 0); // O
        game.makeMove(2, 0); // X wins
        
        assertEquals(TicTacToeGame.GameState.X_WON, game.getGameState());
    }
    
    @Test
    void testOPlayerWin() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 2); // X
        game.makeMove(1, 2); // O wins
        
        assertEquals(TicTacToeGame.GameState.O_WON, game.getGameState());
    }
    
    @Test
    void testDraw() {
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 1); // X
        game.makeMove(2, 0); // O
        game.makeMove(2, 2); // X
        
        assertEquals(TicTacToeGame.GameState.DRAW, game.getGameState());
    }
    
    @Test
    void testNoMovesAfterGameOver() {
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins
        
        assertFalse(game.makeMove(2, 2));
        assertEquals(TicTacToeGame.GameState.X_WON, game.getGameState());
    }
    
    @Test
    void testReset() {
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(0, 1);
        
        game.reset();
        
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        assertEquals(TicTacToeGame.GameState.PLAYING, game.getGameState());
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertNull(game.getCell(row, col));
            }
        }
    }
    
    @Test
    void testGetCellOutOfBounds() {
        assertNull(game.getCell(-1, 0));
        assertNull(game.getCell(0, -1));
        assertNull(game.getCell(3, 0));
        assertNull(game.getCell(0, 3));
    }
}