package gamemodel;

import debug.SkeletonLogger;

public class Door extends MapElement {

	private boolean isOpened = false;

	/*
	 * Opens or closes the door. When the door closes, it destroys the box if
	 * there was on on the field
	 */
	public void setOpened(boolean isOpened) {
		SkeletonLogger.functionCalled(this, "setOpened", new Object[] { isOpened });

		this.isOpened = isOpened;

		if (box != null)
			box.respawn();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player arrives on Door if the door is opened the Player arrives on the
	 * Door MapElement if the door is closed the Player can not arrives on the
	 * Door
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		if (isOpened) {
			player.setPosition(this);
		} else {
			super.handlePlayerArrive(dir, player);
		}

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Does nothing
	 */
	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	/*
	 * Projectile arrives if the Door is opened the projectile goes through it
	 * to the next MapElement if the door is closed the projectile stops on the
	 * Door, waiting for the gc
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		if (!isOpened)
			projectile.destroy();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Put down a box if the door is closed the box position is the player's
	 * position
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

		if (isOpened) {
			box.setPosition(this);

			if (this.box != null)
				this.box.respawn();

			this.box = box;
		} else {
			super.handleBoxPutDown(dir, box);
		}

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public void handleBoxPickUp() {
		SkeletonLogger.functionCalled(this, "handleBoxPickup", null);

		box = null;

		SkeletonLogger.returnFromFunction(null);
	}
}
