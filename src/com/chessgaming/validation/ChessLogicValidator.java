package com.chessgaming.validation;

import java.util.Arrays;
import java.util.List;

import com.chessgaming.enums.ChessGameResult;
import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;
import com.chessgaming.enums.ChessValidationError;
import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.model.ChessBoard;
import com.chessgaming.model.ChessMoveData;
import com.chessgaming.model.ChessPiece;
import com.chessgaming.model.ChessPlayer;

/**
 * Class containing various methods for validating board state
 * Some variables (such as source and target coordinates) are defined on class level to improve readability
 */
public class ChessLogicValidator {
	
	private int sx;
	private int sy;
	private int tx;
	private int ty;

	private ChessBoard chessBoard;
	private ChessPiece[][] board;
	private ChessPiece pieceSource;
	private ChessPiece pieceTarget;

	public ChessLogicValidator(ChessBoard chessBoard) {

		this.chessBoard = chessBoard;
		this.board = chessBoard.getBoard();

	}
	
	public void setupMoveData(ChessMoveData moveData) {
		this.sx = moveData.getSourceX();
		this.sy = moveData.getSourceY();
		this.tx = moveData.getTargetX();
		this.ty = moveData.getTargetY();
		
		
		this.pieceSource = board[sx][sy];
		this.pieceTarget = board[tx][ty];
	}

	public void validateBasicLogic(ChessPlayer nextPlayerToMove) throws ValidationException {
		if (pieceSource == null) {
			throw new ValidationException(ChessValidationError.ERROR_SOURCE_POSITION_NO_CHESS_PIECE);
		}

		if (sx == tx && sy == ty) {
			throw new ValidationException(ChessValidationError.ERROR_SOURCE_TARGET_SQUARES_MUST_BE_DIFFERENT);
		}

		if (pieceTarget != null && pieceTarget.getColor().equals(pieceSource.getColor())) {
			throw new ValidationException(ChessValidationError.ERROR_TARGET_SQUARE_SAME_COLOR_PIECE);
		}

		if (pieceSource.getPlayer() != nextPlayerToMove) {
			throw new ValidationException(ChessValidationError.ERROR_NOT_YOUR_TURN);
		}
	}

	public void validateChessMove() throws ValidationException {

		if (ChessPieceType.PAWN.equals(pieceSource.getType())) {
			if (sx == tx) {
				validatePawnMovement();
			} else if (Math.abs(sx - tx) == 1) {
				validatePawnCapture();
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			}
		} else
		if (ChessPieceType.KNIGHT.equals(pieceSource.getType())) {
			if (!(Math.abs(sx - tx) == 1 && Math.abs(sy - ty) == 2)
			 && !(Math.abs(sx - tx) == 2 && Math.abs(sy - ty) == 1)) {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_KNIGHT_MOVE);
			}
		} else
		if (ChessPieceType.BISHOP.equals(pieceSource.getType())) {
			if (Math.abs(sx - tx) == Math.abs(sy - ty)) {
				validateMovePathDiagonal();
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_BISHOP_MOVE);
			}
		} else
		if (ChessPieceType.ROOK.equals(pieceSource.getType())) {
			if (Math.abs(sx - tx) != 0 && Math.abs(sy - ty) == 0) {
				validateMovePathHorizontal();
			} else if (Math.abs(sx - tx) == 0 && Math.abs(sy - ty) != 0) {
				validateMovePathVertical();
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_ROOK_MOVE);
			}
		} else
		if (ChessPieceType.QUEEN.equals(pieceSource.getType())) {
			if (Math.abs(sx - tx) == Math.abs(sy - ty)) {
				validateMovePathDiagonal();
			} else if (Math.abs(sx - tx) != 0 && Math.abs(sy - ty) == 0) {
				validateMovePathHorizontal();
			} else if (Math.abs(sx - tx) == 0 && Math.abs(sy - ty) != 0) {
				validateMovePathVertical();
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_QUEEN_MOVE);
			}
		} else
		if (ChessPieceType.KING.equals(pieceSource.getType())) {
			if (Math.abs(sx - tx) > 1 || Math.abs(sy - ty) > 1) {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_KING_MOVE);
			}
		}

	}

	private void validatePawnMovement() throws ValidationException {

		if (ChessPieceColor.WHITE.equals(pieceSource.getColor())) {
			if (sy - ty == 1) {
				validateSquareMustBeEmpty(tx, ty);
			} else if (sy - ty == 2 && ty == 4) {
				validateSquareMustBeEmpty(tx, ty);
				validateSquareMustBeEmpty(tx, ty + 1);
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			}
		} else {
			if (sy - ty == -1) {
				validateSquareMustBeEmpty(tx, ty);
			} else
			if (sy - ty == -2 && ty == 3) {
				validateSquareMustBeEmpty(tx, ty);
				validateSquareMustBeEmpty(tx, ty - 1);
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			}
		}
	}

	private void validatePawnCapture() throws ValidationException {
		if (ChessPieceColor.WHITE.equals(pieceSource.getColor())) {
			if (sy - ty == 1) {
				validateSquareMustContainColor(tx, ty, ChessPieceColor.BLACK);
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			}
		} else {
			if (sy - ty == -1) {
				validateSquareMustContainColor(tx, ty, ChessPieceColor.WHITE);
			} else {
				throw new ValidationException(ChessValidationError.ERROR_INVALID_PAWN_MOVE);
			}
		}
	}

	private void validateSquareMustBeEmpty(int x, int y) throws ValidationException {
		if (board[x][y] != null) {
			throw new ValidationException(ChessValidationError.ERROR_PAWN_TARGET_SQUARE_MUST_BE_EMPTY);
		}
	}

	private void validateSquareMustContainColor(int x, int y, ChessPieceColor color) throws ValidationException {
		if (board[x][y] == null || !color.equals(board[x][y].getColor())) {
			throw new ValidationException(ChessValidationError.ERROR_PAWN_TARGET_SQUARE_WRONG_COLOR);
		}
	}

	private void validateMovePathDiagonal() throws ValidationException {
		for (int i = 1; i < Math.abs(sx - tx); i++) {
			int modx = tx > sx ? sx + i : sx - i;
			int mody = ty > sy ? sy + i : sy - i;
			if (board[modx][mody] != null) {
				throw new ValidationException(ChessValidationError.ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY);
			}
		}
	}
	
	private void validateMovePathHorizontal() throws ValidationException {
		for (int i = 1; i < Math.abs(sx - tx); i++) {
			int modx = tx > sx ? sx + i : sx - i;
			if (board[modx][ty] != null) {
				throw new ValidationException(ChessValidationError.ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY);
			}
		}
	}
	
	private void validateMovePathVertical() throws ValidationException {
		for (int i = 1; i < Math.abs(sy - ty); i++) {
			int mody = ty > sy ? sy + i : sy - i;
			if (board[tx][mody] != null) {
				throw new ValidationException(ChessValidationError.ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY);
			}
		}
	}
	
	public boolean isKingInCheck(ChessPlayer player) {
		ChessPiece king = player.getChessPiece(ChessPieceType.KING);
		return isKingInCheck(king);
	}
	
	public boolean isKingInCheck(ChessPiece king) {
		try {
			validateKingNotInCheck(king);
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
	public void validateKingNotInCheck(ChessPlayer player) throws ValidationException {
		ChessPiece king = player.getChessPiece(ChessPieceType.KING);
		validateKingNotInCheck(king);
	}
	
	public void validateKingNotInCheck(ChessPiece king) throws ValidationException {
		validateKingNotInCheckFromPawn(king);
		validateKingNotInCheckFromKing(king);
		validateKingNotInCheckFromKnight(king);
		validateKingNotInCheckDiagonally(king);
		validateKingNotInCheckHorizontally(king);
		validateKingNotInCheckVertically(king);		
	}
	
	private void validateKingNotInCheckFromPawn(ChessPiece king) throws ValidationException {
		if (ChessPieceColor.WHITE.equals(king.getColor())) {
			validateKingCheckFromOpponentsPiece(ChessPieceType.PAWN, king.getColor(), king.getX()-1, king.getY()-1);
			validateKingCheckFromOpponentsPiece(ChessPieceType.PAWN, king.getColor(), king.getX()+1, king.getY()-1);
		} else {
			validateKingCheckFromOpponentsPiece(ChessPieceType.PAWN, king.getColor(), king.getX()-1, king.getY()+1);
			validateKingCheckFromOpponentsPiece(ChessPieceType.PAWN, king.getColor(), king.getX()+1, king.getY()+1);
		}
	}
	
	private void validateKingNotInCheckFromKing(ChessPiece king) throws ValidationException {
		for (int x =-1; x <= 1; x+=1) {
			for (int y =-1; y <= 1; y+=1) {
				validateKingCheckFromOpponentsPiece(ChessPieceType.KING, king.getColor(), x, y);
			}
		}
	}
	
	private void validateKingNotInCheckFromKnight(ChessPiece king) throws ValidationException {		
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()+1, king.getY()+2);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()+1, king.getY()-2);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()-1, king.getY()+2);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()-1, king.getY()-2);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()+2, king.getY()+1);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()+2, king.getY()-1);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()-2, king.getY()+1);
		validateKingCheckFromOpponentsPiece(ChessPieceType.KNIGHT, king.getColor(), king.getX()-2, king.getY()-1);
	}
	
	private void validateKingNotInCheckDiagonally(ChessPiece king) throws ValidationException {
		for (int dir = 1; dir <= 4; dir++) {
			for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) {
				int directionX = (dir > 1 && dir < 4) ? +1 : -1;
				int directionY = (dir > 0 && dir < 3) ? +1 : -1;
				int x = king.getX()+(i*directionX);
				int y = king.getY()+(i*directionY);
				if (isPositionSameColorPiece(x, y, king.getColor())) {
					break;
				} else if (isPathBlockedByOtherPiece(x, y, ChessPieceType.BISHOP, ChessPieceType.QUEEN)) { 
					break;
				} else {
					validateKingCheckFromOpponentsPiece(ChessPieceType.BISHOP, king.getColor(), x, y);
					validateKingCheckFromOpponentsPiece(ChessPieceType.QUEEN, king.getColor(), x, y);
				}
			}
		}
	}
	
	private void validateKingNotInCheckHorizontally(ChessPiece king) throws ValidationException {
		for (int dir =-1; dir <= 1; dir+=2) {
			for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) {
				int x = king.getX()+(i*dir);
				int y = king.getY();
				if (isPositionSameColorPiece(x, y, king.getColor())) {
					break;
				} else if (isPathBlockedByOtherPiece(x, y, ChessPieceType.ROOK, ChessPieceType.QUEEN)) { 
					break;
				} else {
					validateKingCheckFromOpponentsPiece(ChessPieceType.ROOK, king.getColor(), x, y);
					validateKingCheckFromOpponentsPiece(ChessPieceType.QUEEN, king.getColor(), x, y);
				}
			}
		}
	}
	
	private void validateKingNotInCheckVertically(ChessPiece king) throws ValidationException {
		for (int dir =-1; dir <= 1; dir+=2) {
			for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) {
				int x = king.getX();
				int y = king.getY()+(i*dir);
				if (isPositionSameColorPiece(x, y, king.getColor())) {
					break;
				} else if (isPathBlockedByOtherPiece(x, y, ChessPieceType.ROOK, ChessPieceType.QUEEN)) { 
					break;
				} else {
					validateKingCheckFromOpponentsPiece(ChessPieceType.ROOK, king.getColor(), x, y);
					validateKingCheckFromOpponentsPiece(ChessPieceType.QUEEN, king.getColor(), x, y);
				}
			}
		}
	}
	
	private void validateKingCheckFromOpponentsPiece(ChessPieceType pieceType, ChessPieceColor kingColor, int pieceX, int pieceY) throws ValidationException {
		if (isPositionWithinBounds(pieceX, pieceY)) {
			ChessPiece piece = board[pieceX][pieceY];
			if (piece != null && pieceType.equals(piece.getType()) && !kingColor.equals(piece.getColor())) {
				throw new ValidationException(ChessValidationError.ERROR_KING_WOULD_BE_IN_CHECK);
			}
		}
	}
	
	private boolean isPathBlockedByOtherPiece(int x, int y, ChessPieceType... validPieces) {
		if (isPositionWithinBounds(x, y)) {
			ChessPiece piece = board[x][y];
			if (piece != null && !Arrays.stream(validPieces).anyMatch(piece.getType()::equals)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPositionSameColorPiece(int x, int y, ChessPieceColor kingColor) {
		return isPositionWithinBounds(x, y) && board[x][y] != null && kingColor.equals(board[x][y].getColor());
	}
	
	private boolean isPositionWithinBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < ChessBoard.BOARD_SIZE && y < ChessBoard.BOARD_SIZE;
	}
	

	

}
