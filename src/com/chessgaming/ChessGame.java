package com.chessgaming;

import java.io.IOException;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessValidationError;
import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.model.ChessBoard;
import com.chessgaming.model.ChessMoveData;
import com.chessgaming.model.ChessPlayer;
import com.chessgaming.validation.ChessLogicValidator;
import com.chessgaming.validation.GameEndException;
import com.chessgaming.validation.UserInputValidator;
import com.chessgaming.validation.ValidationException;
import com.whitehatgaming.UserInputFile;

/**
 * Main class for playing a game of chess.
 * Handles userInput, validation, display and end-game conditions.
 */
public class ChessGame { 
	
	private ChessBoard chessBoard;
	private ChessPlayer player1;
	private ChessPlayer player2;
	private ChessPlayer nextPlayerToMove;
	
	private InterfaceAsciiGUI asciiGui;
	private UserInputValidator inputValidator;
	private ChessLogicValidator logicValidator;

	public ChessGame() {
		player1 = new ChessPlayer(ChessPieceColor.WHITE);
		player2 = new ChessPlayer(ChessPieceColor.BLACK);
		chessBoard = new ChessBoard(player1, player2);
		
		asciiGui  = new InterfaceAsciiGUI();
		inputValidator = new UserInputValidator();
		logicValidator = new ChessLogicValidator(chessBoard); 
		
		nextPlayerToMove = player1;
	}
	
	public void executeChessMove(int[] userInput) throws ValidationException, GameEndException {
		
		ChessMoveData moveData = new ChessMoveData(userInput, inputValidator);
		ChessPlayer opponent = (nextPlayerToMove == player1 ? player2 : player1);
		
		asciiGui.displayCheckInformation(nextPlayerToMove, logicValidator);
		asciiGui.displayMoveInformation(nextPlayerToMove, moveData);		
		
		logicValidator.setupMoveData(moveData);
		logicValidator.validateBasicLogic(nextPlayerToMove);
		logicValidator.validateChessMove(); 

		chessBoard.saveBoardState();
		chessBoard.changeBoardState(moveData);
		
		logicValidator.validateKingNotInCheck(nextPlayerToMove);
		asciiGui.displayChessBoard(chessBoard);
		logicValidator.simulateGameEnd(opponent, logicValidator);
		nextPlayerToMove = opponent;
	}
	
	public static void main(String[] args) throws IOException {
		
		ChessGame chessGame = new ChessGame();
		UserInputFile userInputFile = new UserInputFile("input/checkmate.txt");
		int[] userInput = userInputFile.nextMove();
		do {
			try {
	
				chessGame.executeChessMove(userInput);
					
			} catch (ValidationException e) {
				System.out.println("Validation Exception: "+e.getErrorMessage().getDescription()+"\n");
				if (ChessValidationError.ERROR_KING_WOULD_BE_IN_CHECK.equals(e.getErrorMessage())) {
					chessGame.revertBoardState();
				}
			} catch (GameEndException e) {
				System.out.println("Game has finished: "+e.getResult().getGuiText());
				return;
			} catch (Exception e) {
				System.out.println("System error - please contact system administrator");
			}
			
			userInput = userInputFile.nextMove();
		} while (userInput != null);
	
	}
	
	public void revertBoardState() {
		chessBoard.revertBoardState();
	}
}
