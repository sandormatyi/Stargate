package controller;

import debug.ProtoLogger;

public class GameRunner {

	/*
	 * Start the game
	 */
	public static Controller startGame() {
		ProtoLogger.disablePrint();

		Game game = new Game("maps/GameMap.txt");
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			ProtoLogger.logError("Unable to create Controller for the game");

		ProtoLogger.enablePrint();
		ProtoLogger.logCommand("------------------------------------------------------------------------------------");
		ProtoLogger.logCommand("Új játék kezdődött");
		ProtoLogger.logCommand("------------------------------------------------------------------------------------");

		// Start the replicator
		controller.moveReplicatorUntilDeath();

		return controller;
	}
}
