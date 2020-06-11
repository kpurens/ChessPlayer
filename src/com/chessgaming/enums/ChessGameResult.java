package com.chessgaming.enums;

public enum ChessGameResult {
	
	CHECKMATE_WHITE_WINS("Checkmate - White wins!"),
	CHECKMATE_BLACK_WINS("Checkmate - Black wins!"),
	DRAW("Draw!");
	
	private final String guiText;
	
	private ChessGameResult(String guiText) {
        this.guiText = guiText;
    }
	
	public String getGuiText() {
		return guiText;
	}
}
