package com.game.play.service;

import org.springframework.stereotype.Service;

import com.game.play.model.Game;
import com.game.play.model.GameMove;

@Service
public class GameService {
	
	public Game startGame() {

		Game game = new Game();
		
		return game;
	}

	public Game registerMove(GameMove move)  {

		Game game = new Game();
		
		return game;
	}
	
	
	public Game gameStatus(String gameId)  {
		
		Game game = new Game();

		return game;
		

}
}