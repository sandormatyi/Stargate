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

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Neither does anything.
	 */
	@Override
	public void handleBoxPickUp(Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPickup", new Object[] { box });

		this.box.setPosition(null);
		this.box = null;

		SkeletonLogger.returnFromFunction(null);
	}
}
