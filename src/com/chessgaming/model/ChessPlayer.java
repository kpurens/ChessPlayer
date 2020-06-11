package com.chessgaming.model;

import java.util.ArrayList;
import java.util.List;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;

/**
 * Class contains information about specific player
 * Player has collection of remaining chess pieces
 * As well as useful methods for initializing / retrieving chess pieces for new game
 */
public class ChessPlayer {
	
	private List<ChessPiece> chessPieces = new ArrayList<ChessPiece>();
	
	public ChessPlayer(ChessPieceColor color) {

		initializeChessPieces(color);
		
	}
	
	private void initializeChessPieces(ChessPieceColor color) {
		for (int x = 0; x < ChessBoard.BOARD_SIZE; x++) {
			int y = ChessPieceColor.WHITE.equals(color) ? 6 : 1;
			chessPieces.add(new ChessPiece(color, ChessPieceType.PAWN, this, x, y));
		}
		
		int y = ChessPieceColor.WHITE.equals(color) ? 7 : 0;
		chessPieces.add(new ChessPiece(color, ChessPieceType.KNIGHT, this, 1, y));
		chessPieces.add(new ChessPiece(color, ChessPieceType.KNIGHT, this, 6, y));
		
		chessPieces.add(new ChessPiece(color, ChessPieceType.BISHOP, this, 2, y));
		chessPieces.add(new ChessPiece(color, ChessPieceType.BISHOP, this, 5, y));
		
		chessPieces.add(new ChessPiece(color, ChessPieceType.ROOK, this, 0, y));
		chessPieces.add(new ChessPiece(color, ChessPieceType.ROOK, this, 7, y));
		
		chessPieces.add(new ChessPiece(color, ChessPieceType.QUEEN, this, 3, y));
		chessPieces.add(new ChessPiece(color, ChessPieceType.KING, this, 4, y));
	}
	
	public ChessPiece getChessPiece(ChessPieceType type) {
		for (ChessPiece chessPiece : chessPieces) {
			if (type.equals(chessPiece.getType())) {
				return chessPiece;
			}
		}
		return null;
	}
	
	public List<ChessPiece> getChessPieces(ChessPieceType type) {
		List<ChessPiece> chessPiecesWithType = new ArrayList<ChessPiece>();
		for (ChessPiece chessPiece : chessPieces) {
			if (type.equals(chessPiece.getType())) {
				chessPiecesWithType.add(chessPiece);
			}
		}
		return chessPiecesWithType;
	}
	
	public List<ChessPiece> getAllRemainingChessPieces() {
		return chessPieces;
	}
	
	public void removeChessPiece(ChessPiece piece) {
		chessPieces.remove(piece);
	}

}
