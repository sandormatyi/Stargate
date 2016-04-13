package controller;

import debug.ProtoLogger;
import gamemodel.Player;
import gamemodel.events.ModelEventSource;

public class Game {

	/*
	 * The controller for the current game
	 */
	private Controller controller = null;

	/*
	 * The score of the player
	 */
	private int score = 0;

	/*
	 * Returns the player's score
	 */
	public int getScore() {
		return score;
	}

	/*
	 * Increments the player's score
	 */
	public void incrementScore() {
		score++;
	}

	/*
	 * Returns the controller for the current game. Valid only when the game is
	 * running!
	 */
	public Controller getController() {
		return controller;
	}

	/*
	 * Start the game
	 */
	public void run() {
		ModelEventSource.clear();

		MapBuilder builder = new MapBuilder();

		builder.buildSkeletonMap();

		controller = builder.createController(this);
	}

	/*
	 * Stop the game. The player that caused the game to end is given as a
	 * parameter
	 */
	public void stop(Player player, boolean isVictory) {
		// TODO: Make a distinction between single- and multiplayer games
		if (isVictory) {
			ProtoLogger.logCommand("A játék véget ért, a játékos nyert");
		} else {
			ProtoLogger.logCommand("A játék véget ért, a játékos vesztett");
		}

		controller = null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
