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
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		player.setPosition(this);

		if (zpm != null) {
			zpm.handlePickUp();
		}

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Does nothing.
	 */
	@Override
	public void handlePlayerLeave() {
		SkeletonLogger.functionCalled(this, "handlePlayerLeave", null);
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Projectile comes, flies through and goes to the next tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player put down a box.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

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
		SkeletonLogger.functionCalled(this, "handleBoxPickup", null);

		box = null;

		SkeletonLogger.returnFromFunction(null);
	}
}
