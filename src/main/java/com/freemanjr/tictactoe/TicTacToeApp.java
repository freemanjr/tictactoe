package com.freemanjr.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
    private TicTacToeGame game;
    private Button[][] buttons;
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        game = new TicTacToeGame();
        buttons = new Button[3][3];
        
        BorderPane root = new BorderPane();
        
        GridPane gameBoard = createGameBoard();
        root.setCenter(gameBoard);
        
        HBox topPanel = createTopPanel();
        root.setTop(topPanel);
        
        HBox bottomPanel = createBottomPanel();
        root.setBottom(bottomPanel);
        
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        updateDisplay();
    }
    
    private GridPane createGameBoard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20));
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setMaxSize(100, 100);
                button.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                
                final int r = row;
                final int c = col;
                button.setOnAction(e -> handleButtonClick(r, c));
                
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }
        
        return grid;
    }
    
    private HBox createTopPanel() {
        statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        HBox panel = new HBox();
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        panel.getChildren().add(statusLabel);
        
        return panel;
    }
    
    private HBox createBottomPanel() {
        Button newGameButton = new Button("New Game");
        newGameButton.setStyle("-fx-font-size: 14px;");
        newGameButton.setOnAction(e -> startNewGame());
        
        HBox panel = new HBox();
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        panel.getChildren().add(newGameButton);
        
        return panel;
    }
    
    private void handleButtonClick(int row, int col) {
        if (game.makeMove(row, col)) {
            updateDisplay();
            
            if (game.getGameState() != TicTacToeGame.GameState.PLAYING) {
                showGameOverDialog();
            }
        }
    }
    
    private void updateDisplay() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TicTacToeGame.Player player = game.getCell(row, col);
                String text = (player == null) ? "" : player.toString();
                buttons[row][col].setText(text);
                
                if (player == TicTacToeGame.Player.X) {
                    buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: blue;");
                } else if (player == TicTacToeGame.Player.O) {
                    buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: red;");
                } else {
                    buttons[row][col].setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
                }
            }
        }
        
        switch (game.getGameState()) {
            case PLAYING -> statusLabel.setText("Current Player: " + game.getCurrentPlayer());
            case X_WON -> statusLabel.setText("Player X Wins!");
            case O_WON -> statusLabel.setText("Player O Wins!");
            case DRAW -> statusLabel.setText("It's a Draw!");
        }
    }
    
    private void showGameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        
        String message = switch (game.getGameState()) {
            case X_WON -> "Player X Wins!";
            case O_WON -> "Player O Wins!";
            case DRAW -> "It's a Draw!";
            default -> "Game Over";
        };
        
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void startNewGame() {
        game.reset();
        updateDisplay();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}