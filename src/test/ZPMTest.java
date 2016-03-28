package test;

import controller.Controller;
import gamemodel.Direction;

class ZPMTest extends Test {

	public ZPMTest(Controller controller) {
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
