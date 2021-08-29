package com.game.play.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMove {
	
	private static final int[][] coordinateConstants = new int[][] {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
	
	
	@NotNull
	String gameId;
	
	@NotNull
	GameOptions player;
	
	@Digits(fraction = 0, integer = 1)
	int position;

	public int[] getCoordinates() {
		
		return coordinateConstants[position];
	}

}
