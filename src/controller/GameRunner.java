package controller;

import debug.GameLogger;

public class GameRunner {

	/*
	 * Start the game
	 */
	public static Controller startGame() {
		GameLogger.disablePrint();

		Game game = new Game("maps/GameMap.txt");
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			GameLogger.logError("Unable to create Controller for the game");

		GameLogger.enablePrint();
		GameLogger.printTitle("Új játék kezdődött");

		// Start the replicator
		controller.moveReplicatorUntilDeath();

		return controller;
	}
}
