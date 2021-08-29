package com.game.play.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.game.play.utils.GameUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMove {
	
	
	@NotNull
	String gameId;
	
	@NotNull
	GameOptions player;
	
	@Digits(fraction = 0, integer = 1)
	int position;

	public int[] getCoordinates() {
		
		return GameUtils.coordinateConstants[position];
	}

}
