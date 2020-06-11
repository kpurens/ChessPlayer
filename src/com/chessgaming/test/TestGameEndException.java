package com.chessgaming.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.chessgaming.ChessGame;
import com.chessgaming.enums.ChessGameResult;
import com.chessgaming.validation.GameEndException;

public class TestGameEndException {

	
	private int[][] gamwWhiteWin = { {4, 6, 4, 4}, {4, 1, 4, 3}, {5, 7, 2, 4}, {1, 0, 2, 2}, {3, 7, 5, 5}, {3, 1, 3, 2}, {5, 5, 5, 1} };
	private int[][] gamwBlackWin = { {5, 6, 5, 5}, {4, 1, 4, 2}, {6, 6, 6, 4}, {5, 0, 4, 1}, {0, 6, 0, 5}, {4, 1, 7, 4} };
	
	@Test
    public void testWhiteWin() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : gamwWhiteWin) {
				chessGame.executeChessMove(userInput);
			}
		} catch (GameEndException e) {
			assertEquals(ChessGameResult.CHECKMATE_WHITE_WINS, e.getResult());
			return;
		}
		fail();
    }
	
	@Test
    public void testBlackWin() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : gamwBlackWin) {
				chessGame.executeChessMove(userInput);
			}
		} catch (GameEndException e) {
			assertEquals(ChessGameResult.CHECKMATE_BLACK_WINS, e.getResult());
			return;
		}
		fail();
    }
}
