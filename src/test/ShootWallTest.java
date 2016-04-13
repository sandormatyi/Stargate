package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class ShootWallTest extends Test {

	/*
	 * Constructor
	 */
	public ShootWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Move SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);

	}

	@Override
	public void run() {
		// Shoot projectile
		controller.shootFirst(PlayerType.ONeill);

	}

}
