package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveWallTest extends Test {

	public PlayerMoveWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Move south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn east
		controller.moveOrTurnPlayer(Direction.EAST);
	}

	@Override
	public void run() {
		// Move east
		controller.moveOrTurnPlayer(Direction.EAST);
	}

}
