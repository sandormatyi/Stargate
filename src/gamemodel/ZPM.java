package gamemodel;

public class ZPM {

	/*
	 * We need to create "game" when the game starts
	 * increments the score variable of Game class
	 * if it is equal to 5 (or 10, whatever) the game stops
	 */
	public void handlePickUp() {
		// TODO
		game.incrementScore();
		if (game.getScore() == 5)
			game.stop();
	}
}
