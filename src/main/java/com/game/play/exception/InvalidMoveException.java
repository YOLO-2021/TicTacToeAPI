package com.game.play.exception;

import lombok.Data;

@Data
public class InvalidMoveException extends TicTacToeException {

    public InvalidMoveException(String message) {
        super(message);
    }

}
