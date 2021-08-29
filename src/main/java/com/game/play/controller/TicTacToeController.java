package com.game.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.play.model.Game;
import com.game.play.model.GameMove;
import com.game.play.service.GameService;

@RestController
public class TicTacToeController {
	
	@Autowired
	private GameService gameService;
	

	
	@PostMapping("/newgame")
	public ResponseEntity<Game> startGame() {
		
		return ResponseEntity.ok(gameService.startGame()) ;
	}
	
	
	@PostMapping("/playgame")
	public ResponseEntity<Game> playGame(@RequestBody GameMove move)  {
		
		return ResponseEntity.ok(gameService.registerMove(move));
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<Game> gameStatus(@PathVariable String gameId)  {
		
		return ResponseEntity.ok(gameService.gameStatus(gameId));
	}
	

}

