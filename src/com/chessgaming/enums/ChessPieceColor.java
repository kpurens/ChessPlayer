package com.chessgaming.enums;

public enum ChessPieceColor {
	
	WHITE("White"),
	BLACK("Black");
	
	private final String guiText;
	
	private ChessPieceColor(String guiText) {
        this.guiText = guiText;
    }
	
	public String getGuiText() {
		return guiText;
	}
}
