package com.chessgaming.model;

import java.io.IOException;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessValidationError;
import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.validation.ChessLogicValidator;
import com.chessgaming.validation.GameEndException;
import com.chessgaming.validation.UserInputValidator;
import com.chessgaming.validation.ValidationException;
import com.whitehatgaming.UserInputFile;

import simulation.ChessSimulator;

/**
 * Main class for playing a game of chess.
 * Handles userInput, validation, display and end-game conditions.
 */
public class ChessGame {
	
	private ChessBoard chessBoard;
	private ChessPlayer playerWhite;
	private ChessPlayer playerBlack;
	private ChessPlayer nextPlayerToMove;
	
	private ChessSimulator simulator;	
	private InterfaceAsciiGUI asciiGui;
	private UserInputValidator inputValidator;
	private ChessLogicValidator logicValidator;

	public ChessGame(InterfaceAsciiGUI asciiGui) {
		
		this.playerWhite = new ChessPlayer(ChessPieceColor.WHITE);
		this.playerBlack = new ChessPlayer(ChessPieceColor.BLACK);
		this.chessBoard = new ChessBoard(playerWhite, playerBlack);
		this.simulator = new ChessSimulator();
		this.asciiGui  = asciiGui;
		this.inputValidator = new UserInputValidator();
		this.logicValidator = new ChessLogicValidator(chessBoard);
		
		nextPlayerToMove = playerWhite;
	}
	
	public void executeChessMove(int[] userInput) throws ValidationException, GameEndException {
		try {	
			
			ChessMoveData moveData = new ChessMoveData(userInput, inputValidator);
			
			asciiGui.displayCheckInformation(nextPlayerToMove, logicValidator);
			asciiGui.displayMoveInformation(nextPlayerToMove, moveData);
			
			logicValidator.setupMoveData(moveData);
			logicValidator.validateBasicLogic(nextPlayerToMove);
			logicValidator.validateChessMove();
	
			chessBoard.saveBoardState();
			chessBoard.changeBoardState(moveData);
			
			logicValidator.validateKingNotInCheck(nextPlayerToMove);
			asciiGui.displayChessBoard(chessBoard);
			
			if (logicValidator.isKingInCheck(getOpponent())) {
				simulator.simulateGameEnd(getOpponent(), chessBoard, logicValidator);
			}
			
			nextPlayerToMove = getOpponent();
		
		} catch (ValidationException e) {
			if (ChessValidationError.ERROR_KING_WOULD_BE_IN_CHECK.equals(e.getErrorMessage())) {
				chessBoard.revertBoardState();
			}
			throw e;
		}
	}
	
	private ChessPlayer getOpponent() {
		return nextPlayerToMove == playerWhite ? playerBlack : playerWhite;
	}
}
