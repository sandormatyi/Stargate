package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveScaleTest extends Test {

	public PlayerMoveScaleTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);

	}

	@Override
	public void run() {
		// Move WEST
		controller.moveOrTurnPlayer(Direction.WEST);

	}

}
