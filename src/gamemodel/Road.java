package gamemodel;

import debug.SkeletonLogger;

public class Road extends MapElement {

	private ZPM zpm = null;

	/*
	 * Zpm setter
	 */
	public void setZpm(ZPM zpm) {
		this.zpm = zpm;
	}

	/*
	 * Player arrival method.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled("handlePlayerArrive", new Object[] { dir, player });

		SkeletonLogger.callFunction(player);
		player.setPosition(this);

		if (zpm != null) {
			SkeletonLogger.callFunction(zpm);
			zpm.handlePickUp();
		}

		SkeletonLogger.returnFromFunction(null);
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
	}

	/*
	 * Player put down a box.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		box.setPosition(this);

		if (this.box != null)
			this.box.respawn();

		this.box = box;
	}

	/*
	 * Neither does anything.
	 */
	@Override
	public void handleBoxPickUp() {
		// TODO
	}
}
