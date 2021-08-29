package com.game.play.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.play.exception.GameNotFoundException;
import com.game.play.exception.TicTacToeException;
import com.game.play.model.Game;
import com.game.play.model.GameMove;
import com.game.play.model.GameStatus;
import com.game.play.store.GameStore;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameServiceUtils gameServiceUtils;

	/**
	 *creates a new game instance and store in DB
	 */
	public Game startGame() {

		return gameServiceUtils.createNewGame();
	}

	/**
	 * validates and register a move for X or O. and returns the Game
	 */
	public Game registerMove(GameMove move) throws TicTacToeException {

		Game game = GameStore.gameDB.get(move.getGameId());
		
		gameServiceUtils.validateMove(move, game);

		int[] coordinates = move.getCoordinates();
		game.getPlayingBoard()[coordinates[0]][coordinates[1]] = move.getPlayer().toString();

		if (gameServiceUtils.isGameOver(game, move)) {
			game.setStatus(GameStatus.FINISHED);
			game.setWinner(move.getPlayer());
		}

		gameServiceUtils.toggleNextPlayer(game);
		return game;
	}
	
	
	/**
	 * returns the game with gameId as input
	 */
	public Game gameStatus(String gameId) throws TicTacToeException {
		
		Game game =  GameStore.gameDB.get(gameId);
		
		if(game == null) {
			throw new GameNotFoundException("Game Not Found : Try with valid gameId ");
		}
		
		return game;
		
	}





}
