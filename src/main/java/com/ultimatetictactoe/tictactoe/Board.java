package com.ultimatetictactoe.tictactoe;

import com.ultimatetictactoe.UltimateTicTacToeGame;
import com.ultimatetictactoe.datastructure.Position;
import com.ultimatetictactoe.datastructure.Winner;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

	private final int NUMBER_OF_SQUARES = 9;
	private boolean gameOver = false;
	private boolean capture = false;
	public int boardCounter;

	private Square[] squares = new Square[NUMBER_OF_SQUARES];
	private Winner winner = Winner.NONE;
	private UltimateTicTacToeGame ultimateTicTacToeGame;

	public Board(UltimateTicTacToeGame ultimateTicTacToeGame) {
		this.ultimateTicTacToeGame = ultimateTicTacToeGame;

		for (int i = 0; i < NUMBER_OF_SQUARES; ++i) {
			squares[i] = new Square(ultimateTicTacToeGame, this, Position.getPositionFromValue(i));
			add(squares[i].button(), i / 3, i % 3);
		}

		setStyle("-fx-border-color: cadetblue; -fx-border-width: 2; -fx-border-radius: 5");
	}

	public void toggleGameStatus(){

		this.gameOver = !this.gameOver;
	}
	
	public void reset(){
		
		this.capture = false;
		this.toggleGameStatus();
		this.winner = Winner.NONE;
		this.boardCounter = 0;
		
		for(Square square : squares)
			square.reset();
	}

	public void evaluate(){
		
	}
	
	public void disable(){
	
		for(Square square : squares)
			square.button().setDisable(true);
	}
	
	public boolean isCaputred(){
		return capture;
	}
	
	public void enable(){
		if(!gameOver){
			for(int i=0; i<squares.length; ++i){
				squares[i].button().setDisable(false);
			}
		}
	}
}
