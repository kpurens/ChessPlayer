package com.chessgaming;

import java.io.IOException;

import com.chessgaming.gui.InterfaceAsciiGUI;
import com.chessgaming.model.ChessGame;
import com.chessgaming.validation.GameEndException;
import com.chessgaming.validation.ValidationException;
import com.whitehatgaming.UserInputFile;

public class Launcher {
	
	public static void main(String[] args) throws IOException {
		
		InterfaceAsciiGUI asciiGui = new InterfaceAsciiGUI();
		ChessGame chessGame = new ChessGame(asciiGui);
		UserInputFile userInputFile = new UserInputFile("input/checkmate.txt");
		int[] userInput = userInputFile.nextMove();
		
		do {
			try {
	
				chessGame.executeChessMove(userInput);
					
			} catch (ValidationException e) {
				asciiGui.display("Validation Exception: " + e.getErrorMessage().getDescription() + "\n");
			} catch (GameEndException e) {
				asciiGui.display("Game has finished: " + e.getResult().getGuiText());
				return;
			} catch (Exception e) {
				asciiGui.display("System error - please contact system administrator");
			}
			
			userInput = userInputFile.nextMove();
			
		} while (userInput != null);
	
	}
}
