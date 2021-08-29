package com.game.play.service;

import org.springframework.stereotype.Service;

import com.game.play.exception.TicTacToeException;
import com.game.play.model.Game;
import com.game.play.model.GameMove;

@Service
public interface GameService {
	
	public Game startGame() ;

	public Game registerMove(GameMove move) throws TicTacToeException ;
	
	public Game gameStatus(String gameId) throws TicTacToeException  ;
	
}