package com.game.play.utils;

import static java.util.Arrays.asList;

import java.util.List;

public class GameUtils {

	public static final int[][] COORDINATE_CONSTANTS = new int[][] {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
	public static final List<List<Integer>> WIN_PATTERNS = asList(asList(0, 1, 2), asList(3, 4, 5), asList(6, 7, 8), asList(0, 4, 8), asList(2, 4, 6),
				asList(0, 3, 6), asList(1, 4, 7), asList(2, 5, 8));


}
