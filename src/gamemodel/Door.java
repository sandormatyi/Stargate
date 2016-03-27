package gamemodel;

public class Door extends MapElement {

	private boolean isOpened = false;

	/*
	 * Opens or closes the door. When the door closes, it destroys the box if
	 * there was on on the field
	 */
	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;

		if (box != null)
			box.respawn();
	}

	/*
	 * Player arrives on Door if the door is opened the Player arrives on the
	 * Door MapElement if the door is closed the Player can not arrives on the
	 * Door
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		if (isOpened) {
			player.setPosition(this);
		} else {
			super.handlePlayerArrive(dir, player);
		}
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
		projectile.setPosition(this);

		if (!isOpened)
			projectile.destroy();
	}

	/*
	 * Put down a box if the door is closed the box position is the player's
	 * position
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		if (isOpened) {
			box.setPosition(this);

			if (this.box != null)
				this.box.respawn();

			this.box = box;
		} else {
			super.handleBoxPutDown(dir, box);
		}
	}

	@Override
	public void handleBoxPickUp() {
		// TODO
	}
}
