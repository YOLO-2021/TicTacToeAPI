package com.game.play.exception;

public class TicTacToeException extends Exception {
	private String message;
	
	public TicTacToeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
