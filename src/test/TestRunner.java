package test;

import controller.Controller;
import controller.Game;
import controller.InputProcessor;
import debug.ProtoLogger;

public class TestRunner {

	/*
	 * Create a game, create the specified test given as a parameter using
	 * TestFactory class and runs it
	 */
	public static void runTest(TestType type) {
		// ProtoLogger.disablePrint();

		Game game = new Game(type.getMapFilePath());
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			return;

		InputProcessor inputProcessor = new InputProcessor(game);

		try {
			ProtoLogger.enablePrint();

			inputProcessor.processInputFile(type.getInputFilePath());
		} catch (Exception e) {
			ProtoLogger.logError("Error happened during test: " + type.name());
			e.printStackTrace();
		}
	}
}
