package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class BoxPickUpScaleTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPickUpScaleTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// move WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// pick up box
		controller.pickUpBox(PlayerType.ONeill);
		// move EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// turn WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// put down box
		controller.putDownBox(PlayerType.ONeill);
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox(PlayerType.ONeill);
	}

}
