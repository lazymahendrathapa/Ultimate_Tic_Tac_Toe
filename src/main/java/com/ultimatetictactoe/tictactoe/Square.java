package com.ultimatetictactoe.tictactoe;

import com.ultimatetictactoe.UltimateTicTacToeGame;
import com.ultimatetictactoe.datastructure.Position;

import javafx.scene.control.Button;

public class Square {

	private static final int SQUARE_LENGTH = 70;
	private Button button = new Button();
	
	public Square(UltimateTicTacToeGame ultimateTicTacToeGame, Board board, Position position){
	
		button.setMinSize(SQUARE_LENGTH, SQUARE_LENGTH);
		
		button.setOnAction(e ->{
			
			if(button.getText().isEmpty()){
				button.setText(ultimateTicTacToeGame.getCurrentPlayer().toString());
				button.setStyle(ultimateTicTacToeGame.getCurrentPlayer().getStyle());
				board.evaluateState();
				ultimateTicTacToeGame.endTurn();
				ultimateTicTacToeGame.getUltimateTicTacToeBoard().disable();
				ultimateTicTacToeGame.getUltimateTicTacToeBoard().enable(position);
			}
			
		});
	}
	
	public Button button(){
		return button;
	}

	public boolean equivalentTo(Square square){
		return !button.getText().isEmpty() && button.getText().equals(square.button.getText());
	}
	
	public void reset(){
		
		button.setText("");
		button.setStyle("");
		button.setDisable(false);
	}
}
