package com.chessgaming.enums;

public enum ChessValidationError {
	
	ERROR_INPUT_MUST_NOT_BE_EMPTY("Input must not be empty!"),
	ERROR_INPUT_INCORRECT_FORMAT("Input must contain exactly 4 numbers!"),
	ERROR_NUMBER_OUT_OF_BOUNDS("Each input number must be within [0-7] range!"),
	ERROR_SOURCE_POSITION_NO_CHESS_PIECE("Chosen coordinates don't contain a chess piece!"),
	ERROR_SOURCE_TARGET_SQUARES_MUST_BE_DIFFERENT("Source and target squares must be different!"),
	ERROR_TARGET_SQUARE_SAME_COLOR_PIECE("Target square must not contain the same color piece!"), 
	ERROR_NOT_YOUR_TURN("It's not your turn!"), 
	ERROR_INVALID_PAWN_MOVE("Invalid pawn move!"), 
	ERROR_INVALID_KNIGHT_MOVE("Invalid knight move!"), 
	ERROR_INVALID_BISHOP_MOVE("Invalid bishop move!"), 
	ERROR_INVALID_ROOK_MOVE("Invalid rook move!"), 
	ERROR_INVALID_QUEEN_MOVE("Invalid queen move!"), 
	ERROR_INVALID_KING_MOVE("Invalid king move!"), 
	ERROR_PAWN_TARGET_SQUARE_MUST_BE_EMPTY("Pawn target movement square must be empty!"),
	ERROR_PAWN_TARGET_SQUARE_WRONG_COLOR("Pawn target square has wrong color!"),
	ERROR_PATH_TOWARDS_TARGET_NOT_EMPTY("Path towards target is not empty!"),
	ERROR_KING_WOULD_BE_IN_CHECK("Can't execute move - king would be in check!")
	;
	
	private final String description;
	
	private ChessValidationError(String description) {
        this.description = description;
    }
	
	public String getDescription() {
		return description;
	}
	
}