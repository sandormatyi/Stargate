package test;

import controller.Controller;
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
		controller.moveOrTurnPlayer(Direction.WEST);
		// turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);
		// shoot
		controller.shootFirst();
		// turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// shoot
		controller.shootSecond();
		// turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// pick up box
		controller.pickUpBox();
		// turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// put down box
		controller.putDownBox();
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox();
	}

}
