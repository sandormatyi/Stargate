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

	/*
	 * Increment Weighcount and Open the Door.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		player.setPosition(this);

		weightCount++;

		if (door != null)
			door.setOpened(true);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Decrement weightCount and close the if it is 0.
	 */
	@Override
	public void handlePlayerLeave() {
		SkeletonLogger.functionCalled(this, "handlePlayerLeave", null);

		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * The projectile harmlessly goes through
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Decrement weightCount and close the if it is 0.
	 */
	@Override
	public void handleBoxPickUp() {
		SkeletonLogger.functionCalled(this, "handleBoxPickUp", null);

		box.setPosition(null);
		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);

		box = null;

		SkeletonLogger.returnFromFunction(null);
	}
	
	/*
	 * Increment Weighcount and Open the Door.
	 */
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
