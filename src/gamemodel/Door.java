package gamemodel;

public class Door extends MapElement {

	private boolean isOpened = false;

	public void setOpened(boolean isOpened) {
		// TODO
		this.isOpened = isOpened;
	}

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
		// TODO
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
