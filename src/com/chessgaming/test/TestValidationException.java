package com.chessgaming.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.chessgaming.ChessGame;
import com.chessgaming.enums.ChessValidationError;
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
	
	@Test
    public void test1() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game1) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INPUT_MUST_NOT_BE_EMPTY, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test2() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game2) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INPUT_INCORRECT_FORMAT, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test3() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game3) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_NUMBER_OUT_OF_BOUNDS, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test4() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game4) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_SOURCE_POSITION_NO_CHESS_PIECE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test5() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game5) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_SOURCE_TARGET_SQUARES_MUST_BE_DIFFERENT, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test6() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game6) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_TARGET_SQUARE_SAME_COLOR_PIECE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test7() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game7) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_NOT_YOUR_TURN, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test8() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game8) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(e.getErrorMessage(), ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			return;
		}
		fail();
    }

	@Test
    public void test9() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game9) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INVALID_KNIGHT_MOVE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test10() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game10) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INVALID_BISHOP_MOVE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test11() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game11) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INVALID_ROOK_MOVE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test12() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game12) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INVALID_QUEEN_MOVE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test13() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game13) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_INVALID_KING_MOVE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test14() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game14) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_PAWN_TARGET_SQUARE_MUST_BE_EMPTY, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test15() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game15) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_TARGET_SQUARE_SAME_COLOR_PIECE, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test16() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game16) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY, e.getErrorMessage());
			return;
		}
		fail();
    }
	
	@Test
    public void test17() throws Exception {
		try {
			ChessGame chessGame = new ChessGame();
			for (int[] userInput : game17) {
				chessGame.executeChessMove(userInput);
			}
		} catch (ValidationException e) {
			assertEquals(ChessValidationError.ERROR_KING_WOULD_BE_IN_CHECK, e.getErrorMessage());
			return;
		}
		fail();
    }
}
