package com.ultimatetictactoe;

public enum Player {

	X("-fx-text-fill: darkred;"), O("-fx-text-fill: royalblue;");

	private final String style;

	private Player(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
}
