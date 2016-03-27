package gamemodel;

public class Road extends MapElement {

	private ZPM zpm = null;

	/*
	 * Player arrival method.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		player.setPosition(this);
		if (zpm != null)
			zpm.handlePickUp();
	}

	/*
	 * Does nothing.
	 */
	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	/*
	 * Projectile comes, flies through and goes to the next tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
		projectile.move();
	}

	/*
	 * Player put down a box.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		box.position = this;
	}

	/*
	 * Neither does anything.
	 */
	@Override
	public void handleBoxPickUp() {
		// TODO
	}
}
