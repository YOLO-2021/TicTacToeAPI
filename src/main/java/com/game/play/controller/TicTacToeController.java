package com.game.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.play.model.Game;
import com.game.play.service.GameService;

@RestController
public class TicTacToeController {
	
	@Autowired
	private GameService gameService;
	
	@PostMapping("/newgame")
	public ResponseEntity<Game> startGame() {
		
		System.out.println("game started ");
		return ResponseEntity.ok(gameService.startGame()) ;
	}
	
	
	@PostMapping("/playgame")
	public ResponseEntity<Game> playGame(@RequestBody String move) {
		
		System.out.println("game in progress ");
		return ResponseEntity.ok(gameService.registerMove(move));
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<Game> gameStatus(@PathVariable String gameId)  {
		
		System.out.println("game status ");
		return ResponseEntity.ok(gameService.gameStatus(gameId));
	}
	

}

