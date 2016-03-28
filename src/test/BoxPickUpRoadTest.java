package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPickUpRoadTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPickUpRoadTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// move WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox();
	}

}
