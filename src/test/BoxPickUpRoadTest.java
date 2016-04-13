package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox(PlayerType.ONeill);
	}

}
