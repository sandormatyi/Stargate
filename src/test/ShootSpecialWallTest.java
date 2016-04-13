package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// Turn NORTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);

	}

	@Override
	public void run() {
		// Shoot projectile
		controller.shootFirst(PlayerType.ONeill);

	}

}
