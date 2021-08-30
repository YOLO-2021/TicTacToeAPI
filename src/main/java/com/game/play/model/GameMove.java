package com.game.play.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
	@Max(8)
	@Min(0)
	int position;

	@JsonIgnore
	public int[] getCoordinates() {
		
		return GameUtils.COORDINATE_CONSTANTS[position];
	}

}
