package test;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

class ZPMTest extends Test {

	/*
	 * Constructor
	 */
	public ZPMTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {
		// Turn WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// Turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// Pick up box
		controller.pickUpBox(PlayerType.ONeill);
		// MOve EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// Turn WEST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
		// Put down box - Open Door
		controller.putDownBox(PlayerType.ONeill);
		// Turn EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// MOve EAST
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
		// Turn NORTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
	}

	@Override
	public void run() {
		// Move NORTH
		controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
	}

}
