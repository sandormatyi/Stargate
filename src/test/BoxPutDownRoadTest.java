package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Step south
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Turn north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// Pick up box
		controller.pickUpBox(PlayerType.ONeill);
	}

	@Override
	public void run() {
		// Put down box
		controller.putDownBox(PlayerType.ONeill);
	}

}
