package com.chessgaming.model;

/**
 * Class represents chess-board with size of BOARD_SIZE
 * Board is implemented as 2D array of ChessPiece instances
 * ChessPiece can be null at specific x/y coordinates
 * backup is used to save board state and revert in case of invalid user input
 */
public class ChessBoard {
	
	public static final int BOARD_SIZE = 8;

	private ChessPiece[][] board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
	private ChessPiece[][] backup = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
	
	public ChessBoard(ChessPlayer player1, ChessPlayer player2) {
		
		addPlayerChessPiecesToBoard(player1);
		addPlayerChessPiecesToBoard(player2);
	}
	
	private void addPlayerChessPiecesToBoard(ChessPlayer player) {
		for (ChessPiece piece : player.getAllRemainingChessPieces()) {
			board[piece.getX()][piece.getY()] = piece;
		}
	}
	
	public void changeBoardState(ChessMoveData moveData) {
		
		int sx = moveData.getSourceX();
		int sy = moveData.getSourceY();
		int tx = moveData.getTargetX();
		int ty = moveData.getTargetY();
		
		if (board[tx][ty] != null) {
			board[tx][ty].getPlayer().removeChessPiece(board[tx][ty]);
		}
		
		board[tx][ty] = board[sx][sy];
		board[tx][ty].setX(tx);
		board[tx][ty].setY(ty);
		board[sx][sy] = null;
	}
	
	public void saveBoardState() {
		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = 0; y < BOARD_SIZE; y++) {
				backup[x][y] = board[x][y];
			}
		}
	}
	
	public void revertBoardState() {
		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = 0; y < BOARD_SIZE; y++) {
				board[x][y] = backup[x][y];
				if (board[x][y] != null) {
					board[x][y].setX(x);
					board[x][y].setY(y);
				}
			}
		}
	}
	
	public ChessPiece[][] getBoard() {
		return board;
	}
	
}
