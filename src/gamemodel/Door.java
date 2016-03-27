package gamemodel;

public class Door extends MapElement {

	private boolean isOpened = false;

	public void setOpened(boolean isOpened) {
		// TODO
		this.isOpened = isOpened;
	}

	/*
	 * Player arrives on Door
	 * if the door is opened the Player arrives on the Door MapElement
	 * if the door is closed the Player can not arrives on the Door
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		// TODO
		if (isOpened == true)
			player.setPosition(this);
		else
			player.setPosition(this.getNeighbour(dir));
	}

	/*
	 * Does nothing
	 */
	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	/*
	 * Projectile arrives
	 * if the Door is opened the projectile goes through it to the next MapElement
	 * if the door is closed the projectile stops on the Door, waiting for the gc
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		// TODO
		projectile.setPosition(this);
		if (isOpened == true)
			projectile.move();
	}

	/*
	 *  Put down a box
	 *  if the door is closed the box position is the player's position
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		// TODO
		if (isOpened = true)
			box.position = this;
		else
			box.position = this.getNeighbour(dir);
	}

	@Override
	public void handleBoxPickUp() {
		// TODO
	}
}
