package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPutDownWormholeTest extends Test {

	/*
	 * Constructor
	 */
	public BoxPutDownWormholeTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Move south
		controller.moveOrTurnPlayer(Direction.SOUTH);
		// Turn West
		controller.moveOrTurnPlayer(Direction.WEST);
		// Shoot
		controller.shootFirst();
		// Turn north
		controller.moveOrTurnPlayer(Direction.NORTH);
		// Pick up box
		controller.pickUpBox();
		// move north
		controller.moveOrTurnPlayer(Direction.NORTH);
		// turn west
		controller.moveOrTurnPlayer(Direction.WEST);
		// move west
		controller.moveOrTurnPlayer(Direction.WEST);
		// turn north
		controller.moveOrTurnPlayer(Direction.NORTH);
		// shoot
		controller.shootSecond();
	}

	@Override
	public void run() {
		// Put down box - Stargate
		controller.putDownBox();
	}

}
