package test;

import controller.Controller;
import controller.Game;
import debug.SkeletonLogger;

public class TestRunner {

	/*
	 * Runs the test given as a parameter
	 */
	public static void runTest(TestType type) {
		Game game = new Game();
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			return;

		Test test = TestFactory.createTest(controller, type);

		SkeletonLogger.disablePrint();
		test.setUp();

		SkeletonLogger.enablePrint();
		test.run();

		SkeletonLogger.disablePrint();
	}
}
