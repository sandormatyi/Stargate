package controller.events;

import controller.PlayerType;

public interface IGameEventListener {
	/*
	 * Called when the game is over
	 */
	void onGameOver(String winner);

	/*
	 * Called when a player picks up a ZPM
	 */
	void onScoreIncreased(PlayerType player);
}
