package gamemodel;

public class Scale extends MapElement {

	private int weightCount = 0;
	private Door door = null;

	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		// TODO
		weightCount++;
	}

	@Override
	public void handlePlayerLeave() {
		// TODO
		weightCount--;
	}

	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		// TODO
	}

	@Override
	public void handleBoxPickUp() {
		// TODO
	}

	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		// TODO
	}
}
