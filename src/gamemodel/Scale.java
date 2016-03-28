package gamemodel;

import debug.SkeletonLogger;

public class Scale extends MapElement {

	private int weightCount = 0;
	private Door door = null;

	/*
	 * Door setter
	 */
	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		player.setPosition(this);

		weightCount++;

		if (door != null)
			door.setOpened(true);

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public void handlePlayerLeave() {
		SkeletonLogger.functionCalled(this, "handlePlayerLeave", null);

		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public void handleBoxPickUp(Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPickUp", new Object[] { box });

		this.box.setPosition(null);
		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);

		this.box = null;

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

		box.setPosition(this);
		weightCount++;

		if (door != null)
			door.setOpened(true);

		if (this.box != null)
			this.box.respawn();

		this.box = box;

		SkeletonLogger.returnFromFunction(null);
	}
}
