package gamemodel;

public class Road extends MapElement {

	private ZPM zpm = null;

	/*
	 * Constructor
	 */
	public Road() {
		super();
	}

	/*
	 * Constructor using fields
	 */
	public Road(ZPM zpm, Box box) {
		super();
		this.zpm = zpm;
		this.box = box;
	}

	/*
	 * Player arrival method.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		player.setPosition(this);
		if (zpm != null)
			zpm.handlePickUp();
	}

	/*
	 * Does nothing.
	 */
	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	/*
	 * Projectile comes, flies through and goes to the next tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
	}

	/*
	 * Player put down a box.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
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
		// TODO
	}
}
