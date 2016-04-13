package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class PlayerMoveRoadTest extends Test {

	/*
	 * Constructor
	 */
	public PlayerMoveRoadTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
	}

	@Override
	public void run() {
		// Move SOUTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
	}

}
