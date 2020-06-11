package com.chessgaming.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.chessgaming.enums.ChessGameResult;
import com.chessgaming.enums.ChessValidationError;
import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.model.ChessGame;
import com.chessgaming.validation.GameEndException;
import com.chessgaming.validation.ValidationException;

public class TestValidationException {

	private int[][] game1 = { { } };
	private int[][] game2 = { { 3, 4, 5, 6, 7} };
	private int[][] game3 = { { 8, 4, 5, 6} };
	private int[][] game4 = { { 4, 4, 5, 5} };
	private int[][] game5 = { { 1, 1, 1, 1} };
	private int[][] game6 = { { 0, 6, 0, 5}, { 0, 1, 0, 2}, { 4, 7, 3, 7} };
	private int[][] game7 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 4, 2, 4, 3} };
	private int[][] game8 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 2, 4} };
	private int[][] game9 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 3, 4}, { 1, 0, 1, 2} };
	private int[][] game10 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 3, 4}, { 2, 0, 2, 2} };
	private int[][] game11 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 3, 4}, { 0, 0, 1, 2} };
	private int[][] game12 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 3, 4}, { 6, 1, 6, 2}, { 3, 7, 1, 4} };
	private int[][] game13 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 6, 3, 4}, { 6, 1, 6, 2}, { 4, 7, 1, 4} };
	private int[][] game14 = { { 4, 6, 4, 4}, { 4, 1, 4, 3}, { 4, 4, 4, 3} };
	private int[][] game15 = { { 3, 6, 3, 4}, { 4, 1, 4, 3}, { 2, 6, 2, 5}, { 0, 1, 0, 2}, { 2, 5, 3, 4} };
	private int[][] game16 = { { 4, 6, 4, 5}, { 4, 1, 4, 2}, { 3, 7, 1, 5} };
	private int[][] game17 = { { 2, 6, 2, 5}, { 4, 1, 4, 2}, { 3, 7, 1, 5}, { 4, 0, 4, 1}, {1, 5, 1, 4}, {0, 1, 0, 2}};
	
	private void simulateChessGame(int[][] gameMoves, ChessValidationError expectedResult) {
		try {
			ChessGame chessGame = new ChessGame(new InterfaceAsciiGUI());
			for (int[] userInput : gameMoves) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(expectedResult, e.getErrorMessage());
			return;
		} catch (Exception e) {
			fail();
		}
		fail();
	}
	
	@Test
    public void test1() throws Exception {
		simulateChessGame(game1, ChessValidationError.ERROR_INPUT_MUST_NOT_BE_EMPTY);
    }
	
	@Test
    public void test2() throws Exception {
		simulateChessGame(game2, ChessValidationError.ERROR_INPUT_INCORRECT_FORMAT);
    }
	
	@Test
    public void test3() throws Exception {
		simulateChessGame(game3, ChessValidationError.ERROR_NUMBER_OUT_OF_BOUNDS);
    }
	
	@Test
    public void test4() throws Exception {
		simulateChessGame(game4, ChessValidationError.ERROR_SOURCE_POSITION_NO_CHESS_PIECE);
    }
	
	@Test
    public void test5() throws Exception {
		simulateChessGame(game5, ChessValidationError.ERROR_SOURCE_TARGET_SQUARES_MUST_BE_DIFFERENT);
    }
	
	@Test
    public void test6() throws Exception {
		simulateChessGame(game6, ChessValidationError.ERROR_TARGET_SQUARE_SAME_COLOR_PIECE);
    }
	
	@Test
    public void test7() throws Exception {
		simulateChessGame(game7, ChessValidationError.ERROR_NOT_YOUR_TURN);
    }
	
	@Test
    public void test8() throws Exception {
		simulateChessGame(game8, ChessValidationError.ERROR_INVALID_PAWN_MOVE);
    }
	
	@Test
    public void test9() throws Exception {
		simulateChessGame(game9, ChessValidationError.ERROR_INVALID_KNIGHT_MOVE);
    }
	
	@Test
    public void test10() throws Exception {
		simulateChessGame(game10, ChessValidationError.ERROR_INVALID_BISHOP_MOVE);
    }
	
	@Test
    public void test11() throws Exception {
		simulateChessGame(game11, ChessValidationError.ERROR_INVALID_ROOK_MOVE);
    }
	
	@Test
    public void test12() throws Exception {
		simulateChessGame(game12, ChessValidationError.ERROR_INVALID_QUEEN_MOVE);
    }
	
	@Test
    public void test13() throws Exception {
		simulateChessGame(game13, ChessValidationError.ERROR_INVALID_KING_MOVE);
    }
	
	@Test
    public void test14() throws Exception {
		simulateChessGame(game14, ChessValidationError.ERROR_PAWN_TARGET_SQUARE_MUST_BE_EMPTY);
    }
	
	@Test
    public void test15() throws Exception {
		simulateChessGame(game15, ChessValidationError.ERROR_TARGET_SQUARE_SAME_COLOR_PIECE);
    }
	
	@Test
    public void test16() throws Exception {
		simulateChessGame(game16, ChessValidationError.ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY);
    }
	
	@Test
    public void test17() throws Exception {
		simulateChessGame(game17, ChessValidationError.ERROR_KING_WOULD_BE_IN_CHECK);
    }
}
