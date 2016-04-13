package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Shoot projectile
		controller.shootFirst(PlayerType.ONeill);
		// Move SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Turn WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// Shoot projectile
		controller.shootSecond(PlayerType.ONeill);
	}

	@Override
	public void run() {
		// Turn WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
	}

}
