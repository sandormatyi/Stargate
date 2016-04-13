package test;

import controller.Controller;
import gamemodel.Direction;

class ShootSpecialWallTest extends Test {

	/*
	 * Constructor
	 */
	public ShootSpecialWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Move WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);

	}

	@Override
	public void run() {
		// Shoot projectile
		controller.shootFirst();

	}

}
