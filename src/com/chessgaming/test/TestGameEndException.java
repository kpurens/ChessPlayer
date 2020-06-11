package com.chessgaming.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.chessgaming.enums.ChessGameResult;
import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.model.ChessGame;
import com.chessgaming.validation.GameEndException;

public class TestGameEndException {

	
	private int[][] gameWhiteWin = { {4, 6, 4, 4}, {4, 1, 4, 3}, {5, 7, 2, 4}, {1, 0, 2, 2}, {3, 7, 5, 5}, {3, 1, 3, 2}, {5, 5, 5, 1} };
	private int[][] gameBlackWin = { {5, 6, 5, 5}, {4, 1, 4, 2}, {6, 6, 6, 4}, {5, 0, 4, 1}, {0, 6, 0, 5}, {4, 1, 7, 4} };
	
	private void simulateChessGame(int[][] gameMoves, ChessGameResult expectedResult) {
		try {
			ChessGame chessGame = new ChessGame(new InterfaceAsciiGUI());
			for (int[] userInput : gameMoves) {
				chessGame.executeChessMove(userInput);
			}
		} catch (GameEndException e) {
			assertEquals(expectedResult, e.getResult());
			return;
		} catch (Exception e) {
			fail();
		}
		fail();
	}
	
	@Test
    public void testWhiteWin() throws Exception {
		simulateChessGame(gameWhiteWin, ChessGameResult.CHECKMATE_WHITE_WINS);
    }
	
	@Test
    public void testBlackWin() throws Exception {
		simulateChessGame(gameBlackWin, ChessGameResult.CHECKMATE_BLACK_WINS);
    }
	
}
