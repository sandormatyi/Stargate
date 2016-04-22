package test;

import controller.Controller;
import controller.Game;
import debug.ProtoLogger;

public class TestRunner {

	/*
	 * Create a game, create the specified test given as a parameter using
	 * TestFactory class and runs it
	 */
	public static void runTest(TestType type) {
		ProtoLogger.disablePrint();

		Game game = new Game(type.getMapFilePath());
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			return;

		Test test = TestFactory.createTest(controller, type);

		if (test == null)
			return;

		try {
			ProtoLogger.enablePrint();
			test.setUp();

			// ProtoLogger.enablePrint();
			test.run();
		} catch (Exception e) {
			ProtoLogger.logError("Error happened during " + test.getClass().getSimpleName());
			e.printStackTrace();
		}
	}
}
