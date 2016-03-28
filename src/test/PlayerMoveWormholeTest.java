package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveWormholeTest extends Test {

	/*
	 * Constructor
	 */
	public PlayerMoveWormholeTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Shoot projectile
		controller.shoot();
		// Move SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Shoot projectile
		controller.shoot();
	}

	@Override
	public void run() {
		// Turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);
	}

}
