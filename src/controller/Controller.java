package controller;

import java.util.HashSet;

import gamemodel.Direction;
import gamemodel.Player;
import gamemodel.ZPM;
import gamemodel.events.IZPMPickedUpListener;
import gamemodel.events.ModelEventSource;

public class Controller implements IZPMPickedUpListener {

	/*
	 * The object representing the current game
	 */
	private Game game;

	/*
	 * The object representing the player character of the game
	 */
	private Player player;

	/*
	 * The set of ZPMs located on the map
	 */
	private HashSet<ZPM> zpmSet;

	/*
	 * Default constructor
	 */
	public Controller(Game game, Player player, HashSet<ZPM> zpmSet) {
		this.game = game;
		this.player = player;
		this.zpmSet = zpmSet;

		ModelEventSource.subscribe(this);
	}

	/*
	 * Move or turn the player depending on the player's current direction and
	 * check if the game is over
	 */
	public void moveOrTurnPlayer(Direction dir) {
		if (player.getDirection() == dir) {
			player.move();
		} else {
			player.turn(dir);
		}

		if (!player.isAlive())
			game.stop(false);
	}

	@Override
	public void onZPMPickedUp(ZPM zpm) {
		game.incrementScore();

		zpmSet.remove(zpm);

		if (zpmSet.isEmpty())
			game.stop(true);
	}
}