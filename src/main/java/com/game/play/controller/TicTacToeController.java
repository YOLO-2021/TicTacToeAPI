package com.game.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.play.exception.TicTacToeException;
import com.game.play.model.Game;
import com.game.play.model.GameMove;
import com.game.play.service.GameService;

@RestController
public class TicTacToeController {
	
	@Autowired
	private GameService gameService;
	

	/**
	 * Creates New Game and returns game entity 
	 * @return Game Entity
	 */
	@PostMapping("/newgame")
	public ResponseEntity<Game> startGame() {
		
		return ResponseEntity.ok(gameService.startGame()) ;
	}
	
	
	/**
	 * @param Gamemove 
	 * @return Game Entity 
	 * @throws TicTacToeException
	 * 
	 * This end point captures the player move records and returns the latest game status
	 * 
	 */
	@PostMapping("/playgame")
	public ResponseEntity<Game> playGame(@RequestBody GameMove move) throws TicTacToeException {
		
		return ResponseEntity.ok(gameService.registerMove(move));
	}
	
	/**
	 * @param gameId
	 * @return Game Entity
	 * @throws TicTacToeException
	 * 
	 * This end point gets gameId as input and returns game status
	 */
	@GetMapping("/{gameId}")
	public ResponseEntity<Game> gameStatus(@PathVariable String gameId) throws TicTacToeException {
		
		return ResponseEntity.ok(gameService.gameStatus(gameId));
	}
	

}

