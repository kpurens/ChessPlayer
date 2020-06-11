package com.chessgaming.validation;

import com.chessgaming.enums.ChessGameResult;

public class GameEndException extends Exception {
	
	private ChessGameResult result;
	
	public GameEndException(ChessGameResult result) {
		this.result = result;
	}
	
	public ChessGameResult getResult() {
		return result;
	}
}
