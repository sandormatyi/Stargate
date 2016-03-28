package test;

import controller.Controller;

class TestFactory {

	/*
	 * Returns a test of the given type
	 */
	public static Test createTest(Controller controller, TestType type) {
		switch (type) {
		// Player movement tests
		case PlayerMoveRoad:
			return new PlayerMoveRoadTest(controller);
		case PlayerMoveScale:
			return new PlayerMoveScaleTest(controller);
		case PlayerMoveWall:
			return new PlayerMoveWallTest(controller);
		case PlayerMoveGap:
			return new PlayerMoveGapTest(controller);
		case PlayerMoveWormhole:
			return new PlayerMoveWormholeTest(controller);

		// Box interaction tests
		case BoxPickUpRoad:
			return new BoxPickUpRoadTest(controller);
		case BoxPickUpScale:
			return new BoxPickUpScaleTest(controller);
		case BoxPickUpWormhole:
			return new BoxPickUpWormholeTest(controller);
		case BoxPutDownRoad:
			return new BoxPutDownRoadTest(controller);
		case BoxPutDownScale:
			return new BoxPutDownScaleTest(controller);
		case BoxPutDownGap:
			return new BoxPutDownGapTest(controller);
		case BoxPutDownWall:
			return new BoxPutDownWallTest(controller);
		case BoxPutDownWormhole:
			return new BoxPutDownWormholeTest(controller);

		// Projectile tests
		case ShootWall:
			return new ShootWallTest(controller);
		case ShootSpecialWall:
			return new ShootSpecialWallTest(controller);

		// ZPM test
		case ZPM:
			return new ZPMTest(controller);

		default:
			return null;
		}
	}
}
