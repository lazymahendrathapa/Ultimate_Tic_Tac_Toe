package com.ultimatetictactoe.tictactoe;

import com.ultimatetictactoe.UltimateTicTacToeGame;
import com.ultimatetictactoe.datastructure.Position;

import javafx.scene.control.Button;

public class Square {

	private final int SQUARE_LENGTH = 70;
	private Button button = new Button();
	
	public Square(UltimateTicTacToeGame ultimateTicTacToeGame, Board board, Position position){
	
		button.setMinSize(SQUARE_LENGTH, SQUARE_LENGTH);
		
		button.setOnAction(e ->{
			
			if(button.getText().isEmpty()){
				
			}
			
		});
	}
	
	public Button button(){
		return button;
	}
	
	public void reset(){
		
		button.setText("");
		button.setStyle("");
		button.setDisable(false);
	}
}
