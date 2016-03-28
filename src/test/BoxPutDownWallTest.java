package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPutDownWallTest extends Test {

	public BoxPutDownWallTest(Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Move SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);
		//Pick up box
		controller.pickUpBox();
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
	}

	@Override
	public void run() {
		//Put down box - Wall
		controller.putDownBox();

	}

}
