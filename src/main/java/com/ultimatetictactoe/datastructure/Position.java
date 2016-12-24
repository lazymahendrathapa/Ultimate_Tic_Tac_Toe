package com.ultimatetictactoe.datastructure;

public enum Position {

	TOP_LEFT(0), TOP_MIDDLE(1), TOP_RIGHT(3), MIDDLE_LEFT(4), MIDDLE_MIDDLE(5), 
	MIDDLE_RIGHT(6), BOTTOM_LEFT(7), BOTTOM_MIDDLE(8), BOTTOM_RIGHT(9);
	
	private int value;

	private Position(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static Position getPositionFromValue(int value){
	
		for(Position position : Position.values())
			if(position.value == value)
				return position;
		
		return null;
	}
	
}
