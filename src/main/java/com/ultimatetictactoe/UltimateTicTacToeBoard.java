package com.ultimatetictactoe;

import com.ultimatetictactoe.datastructure.Position;
import com.ultimatetictactoe.tictactoe.Board;

import javafx.scene.layout.GridPane;

public class UltimateTicTacToeBoard extends GridPane {

	private static final int NUMBER_OF_BOARD = 9;

	private Board[] boards = new Board[NUMBER_OF_BOARD];
	private UltimateTicTacToeGame ultimateTicTacToeGame;
	private int boardCounter = 0;

	public UltimateTicTacToeBoard(UltimateTicTacToeGame ultimateTicTacToeGame) {

		this.ultimateTicTacToeGame = ultimateTicTacToeGame;

		for (int i = 0; i < NUMBER_OF_BOARD; ++i) {
			boards[i] = new Board(ultimateTicTacToeGame);
			add(boards[i], i / 3, i % 3);
		}

		setHgap(5);
		setVgap(5);
	}
	
	public void reset(){

		this.boardCounter = 0;
		for(Board board : boards){
			board.reset();
		}
	}
	
	public void disable(){
	
		for(Board board : boards)
				board.disable();
	}
	
	public void enable(Position position){
	
		for(int i=0; i<Position.values().length; ++i){
			if(position == Position.getPositionFromValue(i)){
			
				if(boards[i].isCaputred())
					this.enableAll();
				else
					boards[i].enable();
			}
		}
	}
	
	private void enableAll(){

		for(Board board : boards)
			board.enable();
	}
	
	public void evalauteState(){
	
		for (int horizontal = 0, vertical = 0; horizontal < NUMBER_OF_BOARD; horizontal += 3, ++vertical)
			if (this.checkSet(vertical, vertical + 3, vertical + 6)
					|| this.checkSet(horizontal, horizontal + 1, horizontal + 2))
				return;

		if (this.checkSet(0, 4, 8) || this.checkSet(2, 4, 6))
			return;

		if (++boardCounter == NUMBER_OF_BOARD) {
			gameEnd();
			ultimateTicTacToeGame.endPrompt("It's a tie!");
			return;
		}
	}
	
	private boolean checkSet(int one, int two, int three){

		if(boardCounter>2 && boards[one].eqnivalentTo(boards[two]) && boards[one].eqnivalentTo(boards[three])){
		
			gameEnd();
			ultimateTicTacToeGame.endPrompt(ultimateTicTacToeGame.checkWinner(boards[one].getWinner().toString()) + "Player wins!");
			return true;
		}
			
		return false;
	}
	
	private void gameEnd(){
	
		for(Board board : boards){
			board.toggleGameStatus();
		}
	}
}
