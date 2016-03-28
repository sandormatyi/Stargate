package gamemodel;

public class Scale extends MapElement {

	private int weightCount;
	private Door door = null;

	/*
	 * Constructor
	 */
	public Scale() {
		super();
		this.weightCount = 0;
	}

	/*
	 * Door setter
	 */
	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		weightCount++;

		if (door != null)
			door.setOpened(true);
	}

	@Override
	public void handlePlayerLeave() {
		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);
	}

	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
	}

	@Override
	public void handleBoxPickUp() {
		weightCount--;

		if (door != null && weightCount < 1)
			door.setOpened(false);

		box = null;
	}

	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		weightCount++;

		if (door != null)
			door.setOpened(true);

		box.setPosition(this);

		if (this.box != null)
			this.box.respawn();

		this.box = box;
	}
}
