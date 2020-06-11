package simulation;

import java.util.List;

import com.chessgaming.enums.ChessGameResult;
import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;
import com.chessgaming.model.ChessBoard;
import com.chessgaming.model.ChessMoveData;
import com.chessgaming.model.ChessPiece;
import com.chessgaming.model.ChessPlayer;
import com.chessgaming.validation.ChessLogicValidator;
import com.chessgaming.validation.GameEndException;
import com.chessgaming.validation.ValidationException;

/**
 * Class designed to simulate game several moves ahead
 * Currently only simulates one move ahead to determine checkmate
 */
public class ChessSimulator {

	
	public void simulateGameEnd(ChessPlayer player, ChessBoard chessBoard, ChessLogicValidator logicValidator) throws GameEndException {
		boolean hasLegalMove = false;
		List<ChessPiece> chessPieces = player.getAllRemainingChessPieces();
		
		for (ChessPiece piece : chessPieces) {
			for (int x = 0; x < ChessBoard.BOARD_SIZE; x++) {
				for (int y = 0; y < ChessBoard.BOARD_SIZE; y++) {
					if (x == piece.getX() && y == piece.getY())continue;
					
					logicValidator.setupMoveData(new ChessMoveData(piece.getX(), piece.getY(), x, y));
					chessBoard.saveBoardState();
					try {
						logicValidator.validateBasicLogic(player);
						logicValidator.validateChessMove();
						
						chessBoard.getBoard()[piece.getX()][piece.getY()] = null;
						chessBoard.getBoard()[x][y] = piece;
						chessBoard.getBoard()[x][y].setX(x);
						chessBoard.getBoard()[x][y].setY(y);
						
						logicValidator.validateKingNotInCheck(player);
					} catch (ValidationException e) {
						chessBoard.revertBoardState();
						continue;
					}
					
					chessBoard.revertBoardState();						
					hasLegalMove = true;
					return;
				}
			}
		}
		
		if (!hasLegalMove) {
			if (ChessPieceColor.WHITE.equals(player.getChessPiece(ChessPieceType.KING).getColor())) {
				throw new GameEndException(ChessGameResult.CHECKMATE_BLACK_WINS);
			} else {
				throw new GameEndException(ChessGameResult.CHECKMATE_WHITE_WINS);
			}
		}
	}
}
