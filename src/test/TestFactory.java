package test;

import controller.Controller;

class TestFactory {

	/*
	 * Returns a test of the given type
	 */
	public static Test createTest(Controller controller, TestType type) {
		// TODO
		switch (type) {
		case PlayerMoveRoad:
			return new PlayerMoveRoadTest(controller);
		default:
			return null;
		}
	}
}
