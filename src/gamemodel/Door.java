package gamemodel;

import debug.ProtoLogger;

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

		// TODO: Kill any player that is on the MapElement
	}

	/*
	 * Player arrives on Door if the door is opened the Player arrives on the
	 * Door MapElement if the door is closed the Player can not arrives on the
	 * Door
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		if (isOpened) {
			ProtoLogger.log("Sikeresen átlépett a következö mezőre: " + this.toString());

			player.setPosition(this);
		} else {
			super.handlePlayerArrive(dir, player);
		}
	}

	/*
	 * Projectile arrives if the Door is opened the projectile goes through it
	 * to the next MapElement if the door is closed the projectile stops on the
	 * Door
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
			ProtoLogger.log("Sikeres dobozletétel a(z) " + this.toString() + " mezőre");

			box.setPosition(this);

			if (this.box != null)
				this.box.respawn();

			this.box = box;
		} else {
			super.handleBoxPutDown(dir, box);
		}
	}

	/*
	 * Handles box pickup
	 */
	@Override
	public void handleBoxPickUp(Box box) {
		ProtoLogger.log("Sikeres dobozfelvétel a(z) " + this.toString() + " mezőről");

		this.box.setPosition(null);
		this.box = null;
	}
}
