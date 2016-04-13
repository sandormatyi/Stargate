package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Move SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Turn NORTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// Pick up box
		controller.pickUpBox(PlayerType.ONeill);
		// Turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
	}

	@Override
	public void run() {
		// Put down box - Wall
		controller.putDownBox(PlayerType.ONeill);

	}

}
