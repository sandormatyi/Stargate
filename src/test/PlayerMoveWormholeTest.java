package test;

import controller.Controller;
import gamemodel.Direction;
class PlayerMoveWormholeTest extends Test {

	public PlayerMoveWormholeTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Move WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);
		//Shoot projectile
		controller.shoot();
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		//Shoot projectile
		controller.shoot();
	}

	@Override
	public void run() {
		// Move south
		controller.moveOrTurnPlayer(Direction.SOUTH);
	}

}
