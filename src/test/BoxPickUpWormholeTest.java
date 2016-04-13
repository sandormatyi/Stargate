package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class BoxPickUpWormholeTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPickUpWormholeTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// move WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// turn NORTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// shoot
		controller.shootFirst(PlayerType.ONeill);
		// turn SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// shoot
		controller.shootSecond(PlayerType.ONeill);
		// turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// pick up box
		controller.pickUpBox(PlayerType.ONeill);
		// turn SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// put down box
		controller.putDownBox(PlayerType.ONeill);
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox(PlayerType.ONeill);
	}

}
