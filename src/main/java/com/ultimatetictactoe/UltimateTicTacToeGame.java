package com.ultimatetictactoe;

import com.ultimatetictactoe.datastructure.Player;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.binding.Bindings;

import javafx.stage.Stage;

public class UltimateTicTacToeGame extends BorderPane {

	private Text currentPlayerText;
	private Player currentPlayer = Player.X;
	private StringProperty currentlyPlaying = new SimpleStringProperty(Player.X.toString());

	private UltimateTicTacToeBoard ultimateTicTacToeBoard;

	public UltimateTicTacToeGame() {

		ultimateTicTacToeBoard = new UltimateTicTacToeBoard(this);
		HBox layout = new HBox();
		MenuBar menuBar = this.generateMenuBar();
		HBox.setHgrow(menuBar, Priority.ALWAYS);
		layout.getChildren().addAll(menuBar, currentPlayerText);
		setTop(layout);
		setCenter(ultimateTicTacToeBoard);
	}

	private MenuBar generateMenuBar() {

		MenuItem newGameMenuItem = new MenuItem("New Game");
		newGameMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.SHIFT_DOWN));
		newGameMenuItem.setOnAction(e -> this.newGame());

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setOnAction(e -> Platform.exit());

		Menu gameMenu = new Menu("Game");
		gameMenu.getItems().addAll(newGameMenuItem, exitMenuItem);

		currentPlayerText = new Text();
		currentPlayerText.textProperty().bind(Bindings.concat(currentlyPlaying).concat(" Player's turn"));

		this.activateMnemonics(gameMenu, newGameMenuItem, exitMenuItem);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(gameMenu);
		return menuBar;
	}

	private void activateMnemonics(MenuItem... items) {
		for (MenuItem item : items)
			item.setMnemonicParsing(true);
	}

	private void newGame() {

		this.currentPlayer = Player.X;
		this.currentlyPlaying.setValue(Player.X.toString());
		this.ultimateTicTacToeBoard.reset();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void endTurn() {

		if (this.currentPlayer == Player.X) {
			this.currentPlayer = Player.O;
			this.currentlyPlaying.setValue(Player.O.toString());
		} else {

			this.currentPlayer = Player.X;
			this.currentlyPlaying.setValue(Player.X.toString());
		}
		
	}

	public UltimateTicTacToeBoard getUltimateTicTacToeBoard() {
		return this.ultimateTicTacToeBoard;
	}

	public void endPrompt(String message) {

		Stage stage = new Stage();
		Label label = new Label(message);
		label.setStyle("-fx-font-weight: bold;");

		final int buttonWidth = 80;

		Button reset = new Button("New Game");
		reset.setMinWidth(buttonWidth);
		reset.setOnAction(e -> {
			stage.close();
			newGame();
		});

		reset.setDefaultButton(true);

		Button quit = new Button("Quit");
		quit.setMinWidth(buttonWidth);
		quit.setOnAction(e -> Platform.exit());

		HBox gameLayout = new HBox(5);
		gameLayout.getChildren().addAll(reset, quit);
		gameLayout.setAlignment(Pos.CENTER);

		VBox layout = new VBox(5);
		layout.getChildren().addAll(label, gameLayout);
		layout.setAlignment(Pos.CENTER);

		stage.setScene(new Scene(layout, 175 + new Text(message).getLayoutBounds().getWidth(), 75));
		stage.sizeToScene();
		stage.setTitle("Game Over");
		ultimateTicTacToeBoard.disable();
		stage.show();
	}

	public String checkWinner(String winner) {

		if (winner.equals("X"))
			return Player.X.toString();
		else
			return Player.O.toString();
	}
}
