package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPutDownWallTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPutDownWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Move SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);
		// Pick up box
		controller.pickUpBox();
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
	}

	@Override
	public void run() {
		// Put down box - Wall
		controller.putDownBox();

	}

}
