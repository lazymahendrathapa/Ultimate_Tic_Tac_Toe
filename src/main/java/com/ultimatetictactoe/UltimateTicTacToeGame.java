package com.ultimatetictactoe;

import com.ultimatetictactoe.datastructure.Player;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.Bindings;

import javafx.stage.Stage;

public class UltimateTicTacToeGame extends BorderPane {

	private StringProperty currentlyPlaying = new SimpleStringProperty("X Player");
	private StringProperty xPlayer = new SimpleStringProperty("X Player");
	private StringProperty oPlayer = new SimpleStringProperty("O Player");

	private Text playingText;
	private IntegerProperty xScore = new SimpleIntegerProperty(0);
	private IntegerProperty oScore = new SimpleIntegerProperty(0);
	private IntegerProperty tieScore = new SimpleIntegerProperty(0);

	private Player currentPlayer = Player.X;
	
	private UltimateTicTacToeBoard ultimateTicTacToeBoard;

	public UltimateTicTacToeGame(Stage stage) {

		ultimateTicTacToeBoard = new UltimateTicTacToeBoard(this);
		HBox layout = new HBox();
		MenuBar menuBar = this.generateMenuBar();
		HBox.setHgrow(menuBar, Priority.ALWAYS);
		layout.getChildren().addAll(menuBar, playingText);
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

		Text xText = new Text();
		xText.textProperty().bind(Bindings.concat(xPlayer).concat(" wins: ").concat(xScore.asString()));

		Text oText = new Text();
		oText.textProperty().bind(Bindings.concat(oPlayer).concat(" wins: ").concat(oScore.asString()));

		Text tieText = new Text();
		tieText.textProperty().bind(Bindings.concat("Ties: ").concat(tieScore.asString()));

		playingText = new Text();
		playingText.textProperty().bind(Bindings.concat(currentlyPlaying).concat("'s turn"));

		VBox scoreLayout = new VBox(5);
		scoreLayout.getChildren().addAll(xText, oText, tieText);
		scoreLayout.setPadding(new Insets(5));
		scoreLayout.setAlignment(Pos.CENTER);

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

		this.ultimateTicTacToeBoard.boardCount = 0;
		this.currentPlayer = Player.X;
		this.currentlyPlaying.setValue(xPlayer.getValue());
		this.ultimateTicTacToeBoard.reset();
	}
	
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
}
