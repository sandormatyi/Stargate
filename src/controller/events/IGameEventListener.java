package controller.events;

import gamemodel.Player;

public interface IGameEventListener {
	/*
	 * Called when the game is over
	 */
	void onGameOver(Player winner);
}
