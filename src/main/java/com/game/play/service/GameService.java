package com.game.play.service;

import org.springframework.stereotype.Service;

import com.game.play.model.Game;

@Service
public class GameService {
	
	public Game startGame() {

		Game game = new Game();
		
		return game;
	}

	public Game registerMove(String move)  {

		Game game = new Game();
		
		return game;
	}
	
	
	public Game gameStatus(String gameId)  {
		
		Game game = new Game();

		return game;
		

}
}