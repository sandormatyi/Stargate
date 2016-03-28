package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPickUpWormholeTest extends Test {

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
		controller.shoot();
		// turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// shoot
		controller.shoot();
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
