package test;

import controller.Controller;
import controller.Game;
import debug.SkeletonLogger;

public class TestRunner {

	/*
	 * Create a game, create the specified test given as a parameter using
	 * TestFactory class and runs it
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
