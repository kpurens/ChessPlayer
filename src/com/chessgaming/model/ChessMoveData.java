package com.chessgaming.model;

import com.chessgaming.validation.UserInputValidator;
import com.chessgaming.validation.ValidationException;

/**
 * Class contains information about single user input
 * Each user input is defined as four variables
 */
public class ChessMoveData {

	private int sourceX;
	private int sourceY;
	private int targetX;
	private int targetY;

	public ChessMoveData(int sx, int sy, int tx, int ty) {
		this.sourceX = sx;
		this.sourceY = sy;
		this.targetX = tx;
		this.targetY = ty;
	}
	
	public ChessMoveData(int[] userInput, UserInputValidator inputValidator) throws ValidationException {
		
		inputValidator.validateSyntax(userInput);

		parseUserInput(userInput);
	}

	private void parseUserInput(int[] userInput) throws ValidationException {
		sourceX = userInput[0];
		sourceY = userInput[1];
		targetX = userInput[2];
		targetY = userInput[3];
	}

	public int getSourceX() {
		return sourceX;
	}

	public void setSourceX(int sourceX) {
		this.sourceX = sourceX;
	}

	public int getSourceY() {
		return sourceY;
	}

	public void setSourceY(int sourceY) {
		this.sourceY = sourceY;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
	
	public String toString() {
		return sourceX+" "+sourceY+" "+targetX+" "+targetY;
	}
	
	
}
