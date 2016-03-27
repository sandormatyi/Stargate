package controller;

import java.util.HashSet;

import gamemodel.Direction;
import gamemodel.Game;
import gamemodel.Player;
import gamemodel.ZPM;

public class GameController {
	
	private Player player;
	
	private Game game;
	
	private HashSet<ZPM> zpmSet;
	
	/*
	 * Move or turn the player depending on the player's current direction and check
	 */
	public void moveOrTurnPlayer(Direction dir) {
		if (player.getDirection() == dir) {
			player.move();
		} else {
			player.turn(dir);
		}
		
		if (!player.isAlive())
			game.stop(false);
		
		if (zpmSet.isEmpty())
			game.stop(true);
	}
}