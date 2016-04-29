package controller;

import java.io.IOException;
import java.util.HashMap;

import controller.MapBuilder.MapBuilderException;
import controller.events.ControllerEventSource;
import debug.GameLogger;
import gamemodel.Player;
import gamemodel.events.ModelEventSource;

public class Game {

	/*
	 * The controller for the current game
	 */
	private Controller controller = null;

	/*
	 * The relative path of the map file for the current game
	 */
	private String mapFile;

	/*
	 * The score of the players
	 */
	private HashMap<Player, Integer> scores = new HashMap<Player, Integer>();

	/*
	 * Constructor for starting a game with a given map file
	 */
	public Game(String mapFile) {
		this.mapFile = mapFile;
	}

	/*
	 * Returns the player's score
	 */
	public int getScore(Player player) {
		Integer score = scores.get(player);

		if (score == null) {
			scores.put(player, 0);
			return 0;
		}

		return score;
	}

	/*
	 * Increments the player's score
	 */
	public void incrementScore(Player player) {
		Integer score = scores.get(player);

		if (score == null) {
			scores.put(player, 1);
		} else {
			scores.put(player, score + 1);
		}
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

		try {
			builder.buildMapFromFile(mapFile);
		} catch (IOException e) {
			GameLogger.logError(e.toString());
		} catch (MapBuilderException e) {
			GameLogger.logError(e.toString());
		}

		controller = builder.createController(this);
	}

	/*
	 * Stop the game. If the game ended with the last ZPM picked up, the player
	 * with the highest score wins. If the game ended with the death of a
	 * player, the surviving player wins.
	 */
	public void stop(boolean isVictory) {
		Player winner = null;

		if (isVictory) {
			int maxScore = -1;

			for (Player p : controller.getPlayers()) {
				int score = getScore(p);
				if (score > maxScore) {
					winner = p;
					maxScore = score;
				} else if (score == maxScore) {
					// If there are more than one winners, it is a draw
					winner = null;
				}
			}
		} else {
			for (Player p : controller.getPlayers()) {
				if (p.isAlive()) {
					// If there are more than one winners, it is a draw
					if (winner != null) {
						winner = null;
						break;
					} else {
						winner = p;
					}
				}
			}
		}

		if (winner != null) {
			GameLogger.logCommand("A játék véget ért, " + winner.toString() + " nyert");
		} else {
			GameLogger.logCommand("A játék véget ért, nincsen győztes");
		}

		// Send notification that the game is over
		ControllerEventSource.notifyGameOver(winner == null ? null : winner.toString());

		controller.gameOver();

		controller = null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
