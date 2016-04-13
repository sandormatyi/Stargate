package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class BoxPutDownGapTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPutDownGapTest(Controller controller) {
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
		// Step north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
	}

	@Override
	public void run() {
		// Put down box
		controller.putDownBox(PlayerType.ONeill);
	}

}
