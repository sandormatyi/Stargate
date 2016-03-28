package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveRoadTest extends Test {

	public PlayerMoveRoadTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
	}

	@Override
	public void run() {
		// Move SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
	}

}
