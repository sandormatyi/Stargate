package controller;

public class Game {

	/*
	 * The controller for the current game
	 */
	private Controller controller = null;

	/*
	 * The score of the player
	 */
	private int score = 0;

	public Game() {
		// TODO
	}

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
		MapBuilder builder = new MapBuilder();

		builder.buildMap();

		controller = builder.createController(this);
	}

	/*
	 * Stop the game with the result given as parameter
	 */
	public void stop(boolean isVictory) {
		controller = null;

		// TODO
	}
}
