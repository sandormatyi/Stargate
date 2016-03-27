package gamemodel;

import java.util.HashMap;

public class SpecialWall extends MapElement {

	private HashMap<Direction, Stargate> stargates = new HashMap<Direction, Stargate>();

	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		// TODO
	}

	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {

	}

	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		// TODO
	}

	@Override
	public void handleBoxPickUp() {
		// TODO
	}
}
