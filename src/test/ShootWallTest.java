package test;

import controller.Controller;
import gamemodel.Direction;

class ShootWallTest extends Test {

	public ShootWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Move SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);

	}

	@Override
	public void run() {
		//Shoot projectile
		controller.shoot();

	}

}
