package com.ultimatetictactoe.tictactoe;

import com.ultimatetictactoe.UltimateTicTacToeGame;
import com.ultimatetictactoe.datastructure.Position;
import com.ultimatetictactoe.datastructure.Winner;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

	private static final int NUMBER_OF_SQUARES = 9;
	private boolean gameOver = false;
	private boolean capture = false;
	private int boardCounter = 0;

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

	public void toggleGameStatus() {

		this.gameOver = !this.gameOver;
	}

	public void reset() {

		this.capture = false;
		this.gameOver = false;
		this.winner = Winner.NONE;
		this.boardCounter = 0;

		for (Square square : squares)
			square.reset();
	}

	public void evaluateState() {

		for (int horizontal = 0, vertical = 0; horizontal < NUMBER_OF_SQUARES; horizontal += 3, ++vertical)
			if (this.checkSet(vertical, vertical + 3, vertical + 6)
					|| this.checkSet(horizontal, horizontal + 1, horizontal + 2))
				return;

		if (this.checkSet(0, 4, 8) || this.checkSet(2, 4, 6))
			return;

		if (++this.boardCounter == NUMBER_OF_SQUARES) {

			winner = Winner.TIE;
			this.boardCapture();
			this.styleBoard();
			return;
		}
	}

	private boolean checkSet(int one, int two, int three) {

		if (this.boardCounter >= 2 && this.squares[one].equivalentTo(this.squares[two])
				&& this.squares[two].equivalentTo(this.squares[three]) && !this.capture) {
			this.winner = this.squares[one].button().getText().equals("X") ? Winner.X : Winner.O;
			this.boardCapture();
			this.styleBoard();
			return true;
		}
		return false;
	}

	private void boardCapture() {

		this.capture = true;
		ultimateTicTacToeGame.getUltimateTicTacToeBoard().evalauteState();
	}

	private void styleBoard() {

		for (Square square : squares)
			square.button().setStyle(winner.getStyle());
	}

	public void disable() {

		for (Square square : squares)
			square.button().setDisable(true);
	}

	public boolean isCaputred() {
		return capture;
	}

	public void enable() {
		if (!gameOver) {
			for (int i = 0; i < squares.length; ++i) {
				squares[i].button().setDisable(false);
			}
		}
	}
	
	public boolean eqnivalentTo(Board target){
		
		return winner != Winner.NONE && (winner == target.winner || target.winner == Winner.TIE);
	}
	
	public Winner getWinner(){
		return winner;
	}
}
