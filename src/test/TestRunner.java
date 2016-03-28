package test;

import controller.Controller;
import controller.Game;
import debug.SkeletonLogger;

public class TestRunner {

	/*
	 * Runs the test given as a parameter
	 */
	public static void runTest(TestType type) {
		SkeletonLogger.disablePrint();

		Game game = new Game();
		game.run();

		Controller controller = game.getController();

		if (controller == null)
			return;

		Test test = TestFactory.createTest(controller, type);

		if (test == null)
			return;

		try {
			test.setUp();

			SkeletonLogger.enablePrint();
			test.run();
		} catch (Exception e) {
			System.err.println("Error happened during " + test.getClass().getSimpleName());
			e.printStackTrace();
		}
	}
}
