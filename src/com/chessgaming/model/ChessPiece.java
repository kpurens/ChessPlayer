package com.chessgaming.model;

import com.chessgaming.enums.ChessPieceColor;
import com.chessgaming.enums.ChessPieceType;

/**
 * Class contains information about single chess piece
 * Each chess piece has color, type, owner(player) and coordinates
 */
public class ChessPiece {
	
	private ChessPieceColor color;
	private ChessPieceType type;
	private ChessPlayer player;	
	private int x;
	private int y;
	
	public ChessPiece(ChessPieceColor color, ChessPieceType type, ChessPlayer player, int x, int y) {
		this.color = color;
		this.type = type;
		this.player = player;
		this.x = x;
		this.y = y;
	}
	
	public ChessPieceType getType() {
		return type;
	}
	public void setType(ChessPieceType type) {
		this.type = type;
	}
	public ChessPlayer getPlayer() {
		return player;
	}
	public void setPlayer(ChessPlayer player) {
		this.player = player;
	}
	public ChessPieceColor getColor() {
		return color;
	}
	public void setColor(ChessPieceColor color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
