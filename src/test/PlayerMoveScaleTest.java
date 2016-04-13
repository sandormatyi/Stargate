package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class PlayerMoveScaleTest extends Test {

	/*
	 * Constructor
	 */
	public PlayerMoveScaleTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
	}

	@Override
	public void run() {
		// Move WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);

	}

}
