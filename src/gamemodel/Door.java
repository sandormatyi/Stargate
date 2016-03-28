package gamemodel;

import debug.SkeletonLogger;

public class Door extends MapElement {

	private boolean isOpened = false;

	/*
	 * Opens or closes the door. When the door closes, it destroys the box if
	 * there was on on the field
	 */
	public void setOpened(boolean isOpened) {
		// Signal that the setOpened method was called
		SkeletonLogger.functionCalled(this, "setOpened", new Object[] { isOpened });
		// Set the Door opened
		this.isOpened = isOpened;
		// If there was a box on this MapElement, then destroy that.
		if (box != null)
			box.respawn();
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player arrives on Door if the door is opened the Player arrives on the
	 * Door MapElement if the door is closed the Player can not arrives on the
	 * Door
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		// Signal that the handlePlayerArrive method was called
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });
		// If it was opened, then it equals to a road, otherwise it is a wall.
		if (isOpened) {
			player.setPosition(this);
		} else {
			super.handlePlayerArrive(dir, player);
		}
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Projectile arrives if the Door is opened the projectile goes through it
	 * to the next MapElement if the door is closed the projectile stops on the
	 * Door, waiting for the gc
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		// Signal that the handleProjectileArrive method was called
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });
		// set the Projectile position to this MapElement
		projectile.setPosition(this);
		// If it is not opened, then destroy the Projectile
		if (!isOpened)
			projectile.destroy();
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Put down a box if the door is closed the box position is the player's
	 * position
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		// Signal that the handleBoxPutDown method was called
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });
		// If it is opened, then
		if (isOpened) {
			box.setPosition(this);
			// If there is a box already on this MapElement, then destroy that.
			if (this.box != null)
				this.box.respawn();
			// Modify the MapElement's box
			this.box = box;
		} else {
			super.handleBoxPutDown(dir, box);
		}
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Handles box pickup
	 */
	@Override
	public void handleBoxPickUp() {
		// Signal that the handleBoxPickUp method was called
		SkeletonLogger.functionCalled(this, "handleBoxPickup", null);
		// Set the Box's position to null
		box.setPosition(null);
		// Modify the MapElement's box to null
		box = null;
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}
}
