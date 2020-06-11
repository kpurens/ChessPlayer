package com.chessgaming.enums;

public enum ChessPieceType {
	
	PAWN("P"),
	KNIGHT("N"),
	BISHOP("B"),
	ROOK("R"),
	KING("K"),
	QUEEN("Q");
	
	private final String guiSymbol;
	
	private ChessPieceType(String guiSymbol) {
        this.guiSymbol = guiSymbol;
    }
	
	public String getGuiSymbol() {
		return guiSymbol;
	}
	
}