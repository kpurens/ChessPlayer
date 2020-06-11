package com.chessgaming.validation;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;
import com.chessgaming.enums.ChessValidationError;
import com.chessgaming.model.ChessBoard;
import com.chessgaming.model.ChessMoveData;
import com.chessgaming.model.ChessPiece;
import com.chessgaming.model.ChessPlayer;

public class UserInputValidator {
	
	public void validateSyntax(int[] userInput) throws ValidationException {
		
		if (userInput == null || userInput.length <= 0) {
			throw new ValidationException(ChessValidationError.ERROR_INPUT_MUST_NOT_BE_EMPTY);
		}
		
		if (userInput.length != 4) {
			throw new ValidationException(ChessValidationError.ERROR_INPUT_INCORRECT_FORMAT);
		}
		
		for (int i : userInput) {
			if (i < 0 || i > 7) {
				throw new ValidationException(ChessValidationError.ERROR_NUMBER_OUT_OF_BOUNDS);
			}
		}
	}

}
