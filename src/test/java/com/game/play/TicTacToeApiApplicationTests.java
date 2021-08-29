package com.game.play;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.play.controller.TicTacToeController;
import com.game.play.model.Game;

@SpringBootTest
@RunWith(SpringRunner.class)
class TicTacToeApiApplicationTests {

	@Autowired
	TicTacToeController controller;

	@Test
	public void testNewGame() {
		
		ResponseEntity<Game> s = controller.startGame();
		assertEquals(HttpStatus.OK, s.getStatusCode());
		
	}
	
	@Test
	public void testPlayGame() {
		
		ResponseEntity<Game> s = controller.playGame("X");
		assertEquals(HttpStatus.OK, s.getStatusCode());
		
	}
	
	@Test
	public void testGameStatus() {
		
		ResponseEntity<Game> s = controller.gameStatus("id");
		assertEquals(HttpStatus.OK, s.getStatusCode());
		
	}
	

}
