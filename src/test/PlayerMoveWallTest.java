package test;

import controller.Controller;
import gamemodel.Direction;

class PlayerMoveWallTest extends Test {

	/*
	 * Constructor
	 */
	public PlayerMoveWallTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// Pick up box
		controller.pickUpBox();
		// MOve EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// Turn WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Put down box - Open Door
		controller.putDownBox();
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// MOve EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
	}

	@Override
	public void run() {
		// Move south
		controller.moveOrTurnPlayer(Direction.SOUTH);
	}

}
