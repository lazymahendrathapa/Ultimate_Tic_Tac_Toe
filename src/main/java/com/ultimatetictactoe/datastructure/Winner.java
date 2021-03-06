package com.ultimatetictactoe.datastructure;

public enum Winner {

	NONE(""), X("-fx-color: darkred;"), O("-fx-color: royalblue;"), TIE("-fx-color: indigo");

	private final String style;

	private Winner(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
}
