package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPickUpScaleTest extends Test {

	public BoxPickUpScaleTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// move WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// pick up box
		controller.pickUpBox();
		// move EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// put down box
		controller.putDownBox();
	}

	@Override
	public void run() {
		// pick up box
		controller.pickUpBox();
	}

}
