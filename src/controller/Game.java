package controller;

public class Game {

	/*
	 * The score of the player
	 */
	private int score = 0;

	/*
	 * Singleton instance
	 */
	private static Game instance;

	public Game() {
		// TODO
	}

	/*
	 * Singleton accessor method
	 */
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();

		return instance;
	}

	/*
	 * Returns the player's score
	 */
	public int getScore() {
		// TODO
		return score;
	}

	/*
	 * Increments the player's score
	 */
	public void incrementScore() {
		// TODO
		score++;
	}

	/*
	 * Start the game
	 */
	public void run() {
		// TODO: Read and build the map and create the Controller object for the
		// current game
	}

	/*
	 * Stop the game with the result given as parameter
	 */
	public void stop(boolean isVictory) {
		// TODO
	}
}
