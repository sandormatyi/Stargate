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

	}

	@Override
	public void run() {
		// Move south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn east
		controller.moveOrTurnPlayer(Direction.EAST);
		// Move east
		controller.moveOrTurnPlayer(Direction.EAST);
	}

}
