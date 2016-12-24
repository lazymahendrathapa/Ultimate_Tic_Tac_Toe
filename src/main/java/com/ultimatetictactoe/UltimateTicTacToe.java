package com.ultimatetictactoe;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UltimateTicTacToe extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		stage.setTitle("Ultimate Tic Tac Toe");
		Scene scene = new Scene(new UltimateTicTacToeGame(stage));
		scene.getStylesheets().add("tictactoe.css");
		stage.setScene(scene);
		stage.getIcons().add(new Image("icon.png"));
		stage.show();
	}

}
