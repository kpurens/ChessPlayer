package com.chessgaming.gui;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;
import com.chessgaming.model.ChessBoard;
import com.chessgaming.model.ChessMoveData;
import com.chessgaming.model.ChessPiece;
import com.chessgaming.model.ChessPlayer;
import com.chessgaming.validation.ChessLogicValidator;

/**
 * Graphical user interface
 * Outputs chess board as ASCII symbol grid
 */
public class InterfaceAsciiGUI {

	public void displayMoveInformation(ChessPlayer chessPlayer, ChessMoveData moveData) {
		
		ChessPieceColor playerColor = chessPlayer.getChessPiece(ChessPieceType.KING).getColor();
		
		System.out.println("Player " + playerColor.getGuiText() + " makes a move: " + moveData.toString() + "\n");
	}
	
	public void displayChessBoard(ChessBoard chessBoard) {
		
		for (int y = 0; y < ChessBoard.BOARD_SIZE; y++) {
			for (int x = 0; x < ChessBoard.BOARD_SIZE; x++) {
				ChessPiece chessPiece = chessBoard.getBoard()[x][y];
				if (chessPiece != null) {
					if (ChessPieceColor.BLACK.equals(chessPiece.getColor())) {
						System.out.print(chessPiece.getType().getGuiSymbol().toLowerCase() + " ");
					} else {
						System.out.print(chessPiece.getType().getGuiSymbol() + " ");
					}
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public void displayCheckInformation(ChessPlayer nextPlayerToMove, ChessLogicValidator logicValidator) {
		if (logicValidator.isKingInCheck(nextPlayerToMove)) {
			System.out.println("in check");
		}
	}
	
	public void display(String information) {
		System.out.println(information);
	}
}
