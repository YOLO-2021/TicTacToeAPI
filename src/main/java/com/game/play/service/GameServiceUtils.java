package com.game.play.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.game.play.exception.GameNotFoundException;
import com.game.play.exception.InvalidMoveException;
import com.game.play.exception.TicTacToeException;
import com.game.play.model.Game;
import com.game.play.model.GameMove;
import com.game.play.model.GameOptions;
import com.game.play.model.GameStatus;
import com.game.play.store.GameStore;
import com.game.play.utils.GameUtils;

@Service
public class GameServiceUtils {
	
	/**
	 * create a new game and set required values 
	 * @return
	 */
	public Game createNewGame() {
		
		Game game = new Game();
		game.setGameId(UUID.randomUUID().toString());
		game.setPlayingBoard(new String[3][3]);
		game.setWhosNext(GameOptions.X);
		game.setStatus(GameStatus.STARTED);
		GameStore.gameDB.put(game.getGameId(), game);
		return game;
	}
	
	
	/**
	 * validate player move to check if
	 *  game is valid. 
	 * Game is still not finished
	 * Player dont re-enter in same position 
	 * correct player does the action
	 * 
	 * @param move
	 * @param game
	 * @return
	 * @throws TicTacToeException
	 */
	public boolean validateMove(GameMove move, Game game) throws TicTacToeException {
		
		if(game == null) {
			throw new GameNotFoundException("Game Not Found : Try with valid gameId ");
		}
		
		if (!isValidMove(game, move)) {
			throw new InvalidMoveException("Invalid Move : Try again ");
		}
		
		return true;
	}
	

	/**
	 * check if game is over in case 
	 * a pattern is achieved by X or O
	 * all 9 position are filled with out a winner
	 * @param game
	 * @param move
	 * @return
	 */
	public boolean isGameOver(Game game, GameMove move) {

		String[][] playingBoard = game.getPlayingBoard();
		int playedPosition = move.getPosition();
		
		List<List<Integer>> patternsToVerify = GameUtils.winPatterns.stream().filter(x -> x.contains(playedPosition) == true)
				.collect(Collectors.toList());

		for (List<Integer> lst : patternsToVerify) {
			int[] coordinate1 = GameUtils.coordinateConstants[lst.get(0)];
			int[] coordinate2 = GameUtils.coordinateConstants[lst.get(1)];
			int[] coordinate3 = GameUtils.coordinateConstants[lst.get(2)];

			if (move.getPlayer().toString().equals(playingBoard[coordinate1[0]][coordinate1[1]])
					&& move.getPlayer().toString().equals(playingBoard[coordinate2[0]][coordinate2[1]])
					&& move.getPlayer().toString().equals(playingBoard[coordinate3[0]][coordinate3[1]])) {
				return true;
			}

		}
		
		boolean isFreeCellAvailable = false;
		for(int i =0 ; i <3 ; i++) {
			for(int j=0 ; j < 3 ; j++) {
				if(playingBoard[i][j] == null) {
					isFreeCellAvailable = true;
				}
			}
		}
		
		if(!isFreeCellAvailable) {
			return true;
		}

		return false;
	}
	
	public void toggleNextPlayer(Game game) {

		if (GameOptions.X == game.getWhosNext()) {
			game.setWhosNext(GameOptions.O);
			return;
		}

		if (GameOptions.O == game.getWhosNext()) {
			game.setWhosNext(GameOptions.X);
			return;
		}

	}
	
	
	private boolean isValidMove(Game game, GameMove move) {

		if (game.getStatus() == GameStatus.FINISHED) {
			return false;
		}

		if (game.getWhosNext() != move.getPlayer()) {
			return false;
		}
		
		int[] coordinates = move.getCoordinates();
		if (game.getPlayingBoard()[coordinates[0]][coordinates[1]] != null) {
			return false;
		}

		return true;
	}

}
