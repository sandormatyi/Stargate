package test;

import controller.Controller;
import gamemodel.Direction;

class BoxPutDownWormholeTest extends Test {

	public BoxPutDownWormholeTest(Controller controller) {
		super(controller);
	}

	@Override
	public void setUp() {	
		// Move WEST
		controller.moveOrTurnPlayer(Direction.WEST);
		// Turn EAST
		controller.moveOrTurnPlayer(Direction.EAST);
		//Pick up box
		controller.pickUpBox();
		// Turn NORTH
		controller.moveOrTurnPlayer(Direction.NORTH);
		//Shoot projectile
		controller.shoot();
		// Turn SOUTH
		controller.moveOrTurnPlayer(Direction.SOUTH);
		//Shoot projectile - Open Stargate
		controller.shoot();
	}

	@Override
	public void run() {
		//Put down box - Stargate
		controller.putDownBox();

	}

}
