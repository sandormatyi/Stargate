package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPutDownRoadTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPutDownRoadTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Step south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn north
		controller.moveOrTurnPlayer(Direction.NORTH);
		// Pick up box
		controller.pickUpBox();
	}

	@Override
	public void run() {
		// Put down box
		controller.putDownBox();
	}

}
