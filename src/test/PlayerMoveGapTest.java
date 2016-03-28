package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveGapTest extends Test {

	public PlayerMoveGapTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn north
		controller.moveOrTurnPlayer(Direction.NORTH);

	}

	@Override
	public void run() {
		controller.moveOrTurnPlayer(Direction.NORTH);

	}

}
