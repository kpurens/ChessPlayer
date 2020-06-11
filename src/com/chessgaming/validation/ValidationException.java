package com.chessgaming.validation;

import com.chessgaming.enums.ChessValidationError;

public class ValidationException extends Exception {

	private ChessValidationError errorMessage;
	
	public ValidationException(ChessValidationError errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ChessValidationError getErrorMessage() {
		return errorMessage;
	}
}
