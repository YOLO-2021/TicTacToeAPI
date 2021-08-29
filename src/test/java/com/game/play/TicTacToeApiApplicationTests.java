package com.game.play;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.play.controller.TicTacToeController;
import com.game.play.exception.GameNotFoundException;
import com.game.play.exception.InvalidMoveException;
import com.game.play.exception.TicTacToeException;
import com.game.play.model.Game;
import com.game.play.model.GameMove;
import com.game.play.model.GameOptions;
import com.game.play.model.GameStatus;

@SpringBootTest
@RunWith(SpringRunner.class)
class TicTacToeApiApplicationTests {

	@Autowired
	TicTacToeController controller;
	

	/**
	 * Positive test case 
	 * Test if the new game is created 
	 * Expectation : once created, a unique ID is generated , status set as STARTED, playing board is created and who is next is set 
	 */
	@Test
	public void testNewGame() {
		
		ResponseEntity<Game> respEntity = controller.startGame();
		Game game = (Game)respEntity.getBody();
		
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		assertNotNull(game.getGameId());
		assertEquals(GameStatus.STARTED,game.getStatus());
		assertNotNull(game.getWhosNext());
		assertNotNull(game.getPlayingBoard());
		
	}
	
	/**
	 * Positive Test Case 
	 * Test for the first move 
	 * Expectation : after the first move, the who is next switches from X to O.
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario1() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		GameMove move = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);

		ResponseEntity<Game> respEntity = controller.playGame(move);
		Game game = (Game)respEntity.getBody();
		
		assertEquals(GameOptions.O, game.getWhosNext());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	/**
	 * Positive Test Case 
	 * Test for  move 
	 * Expectation : after a pattern is formed, the game should be FINISHED and winner should be declared as X
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario2() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		
		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,5);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,2);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		ResponseEntity<Game> respEntity = controller.playGame(move5);
		Game game = (Game)respEntity.getBody();
		
		assertEquals(GameStatus.FINISHED, game.getStatus());
		assertEquals(GameOptions.X, game.getWinner());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	
	/**
	 * Positive Test Case 
	 * Test for  move 
	 * Expectation : after a pattern is formed, the game should be FINISHED and winner should be declared as O
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario3() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();

		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,5);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,8);
		GameMove move6 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,3);
		
		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		controller.playGame(move5);
		ResponseEntity<Game> respEntity = controller.playGame(move6);
		Game game = (Game)respEntity.getBody();
		
		assertEquals(GameStatus.FINISHED, game.getStatus());
		assertEquals(GameOptions.O, game.getWinner());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	/**
	 * Positive Test Case 
	 * Test for   move 
	 * Expectation : after a pattern is formed, the game should be FINISHED and winner should be declared as X - pattern Across
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario4() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();

		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,1);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,4);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,2);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,8);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		ResponseEntity<Game> respEntity = controller.playGame(move5);
		Game game = (Game)respEntity.getBody();
		
		assertEquals(GameStatus.FINISHED, game.getStatus());
		assertEquals(GameOptions.X, game.getWinner());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	/**
	 * Positive Test Case 
	 * Test for  move 
	 * Expectation : after all positions are filled the game is marked finished
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario5() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();

		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,1);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,2);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,3);
		GameMove move6 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,5);
		GameMove move7 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,7);
		GameMove move8 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,6);
		GameMove move9 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,8);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		controller.playGame(move5);
		controller.playGame(move6);
		controller.playGame(move7);
		controller.playGame(move8);
		ResponseEntity<Game> respEntity = controller.playGame(move9);
		Game game = (Game)respEntity.getBody();
		
		assertEquals(GameStatus.FINISHED, game.getStatus());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	/**
	 * positive test cases 
	 * Test for game status
	 * A game is created and when the game is queried using the ID , the game set is returned 
	 * @throws TicTacToeException 
	 */
	@Test
	public void testGameStatus_scenario1() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();

		ResponseEntity<Game> respEntity = controller.gameStatus(respEntityGame.getBody().getGameId());
		assertEquals(respEntityGame.getBody().getGameId(), respEntity.getBody().getGameId());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
		
	}
	
	/**
	 * Negative test case
	 * Test for game status
	 * A game is created and when the game is queried using the ID , the game set is returned 
	 * @throws TicTacToeException 
	 */
	@Test
	public void testGameStatus_scenario2() throws TicTacToeException {

		Assertions.assertThrows(GameNotFoundException.class,()-> controller.gameStatus("dummy"));
		
	}
	
	
	/**
	 * Negative Test Case 
	 * Test for the faulty  move 
	 * Expectation : when O tried to enter twice. return Bad request 
	 * @throws TicTacToeException 
	 */
	//@Test(expected  = InvalidMoveException.class)
	@Test
	public void testPlayGame_Scenario10() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		
		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,5);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,2);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		Assertions.assertThrows(InvalidMoveException.class, () -> controller.playGame(move5));

	}
	
	/**
	 * Negative Test Case 
	 * Test for the faulty  move 
	 * Expectation : when X tries to enter in already filled in place
	 * @throws TicTacToeException 
	 */
	//@Test(expected  = InvalidMoveException.class)
	@Test
	public void testPlayGame_Scenario11() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		
		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,5);
		GameMove move5 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,5);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		controller.playGame(move4);
		Assertions.assertThrows(InvalidMoveException.class, () -> controller.playGame(move5));

	}
	
	/**
	 * Negative Test Case 
	 * Test for the faulty  move 
	 * Expectation : when X tries to enter twice
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario12() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		
		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,5);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		Assertions.assertThrows(InvalidMoveException.class, () -> controller.playGame(move4));
		
	}
	
	/**
	 * Negative Test Case 
	 * Test for the faulty  move 
	 * Expectation : when X tries to enter 10
	 * @throws TicTacToeException 
	 */
	@Test
	public void testPlayGame_Scenario13() throws TicTacToeException {
		
		ResponseEntity<Game> respEntityGame = controller.startGame();
		
		GameMove move1 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,0);
		GameMove move2 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.O,4);
		GameMove move3 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,1);
		GameMove move4 = new GameMove(respEntityGame.getBody().getGameId(),GameOptions.X,10);

		controller.playGame(move1);
		controller.playGame(move2);
		controller.playGame(move3);
		Assertions.assertThrows(Exception.class, () -> controller.playGame(move4));
		
	}
	

}
