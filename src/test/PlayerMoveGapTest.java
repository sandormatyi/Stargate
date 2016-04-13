package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class PlayerMoveGapTest extends Test {

	/*
	 * Constructor
	 */
	public PlayerMoveGapTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
	}

	@Override
	public void run() {
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
	}
}
