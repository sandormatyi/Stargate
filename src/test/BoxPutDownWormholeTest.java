package test;

import controller.Controller;
import controller.PlayerType;
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
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Move south
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
		// Turn West
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// Shoot
		controller.shootFirst(PlayerType.ONeill);
		// Turn north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// Pick up box
		controller.pickUpBox(PlayerType.ONeill);
		// move north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// turn west
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// move west
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// turn north
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
		// shoot
		controller.shootSecond(PlayerType.ONeill);
	}

	@Override
	public void run() {
		// Put down box - Stargate
		controller.putDownBox(PlayerType.ONeill);
	}

}
