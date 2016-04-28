package controller.events;

import gamemodel.Player;

public interface IGameEventListener {
	/*
	 * Called when a ZPM has been picked up
	 */
	void onZPMPickedUp(Player player);

	/*
	 * Called when the game is over
	 */
	void onGameOver(Player winner);
}
