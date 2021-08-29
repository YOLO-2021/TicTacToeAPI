package com.game.play.exception;

import lombok.Data;

@Data
public class GameNotFoundException extends TicTacToeException {

    public GameNotFoundException(String message) {
        super(message);
    }

}
