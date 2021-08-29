package com.game.play.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {
	
    private String gameId;
    private GameStatus status;
    private GameOptions whosNext; 
    private GameOptions winner; 
    private String[][] playingBoard;	


}