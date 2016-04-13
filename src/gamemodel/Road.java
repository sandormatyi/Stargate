package gamemodel;

import debug.ProtoLogger;

public class Road extends MapElement {

	/*
	 * Constructor inherited from base class
	 */
	public Road(String coord) {
		super(coord);
	}

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
		ProtoLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		player.setPosition(this);

		if (zpm != null) {
			zpm.handlePickUp();
		}
	}

	/*
	 * Projectile comes, flies through and goes to the next tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);

		if (replicator != null)
			replicator.destroy();
	}

	/*
	 * A replicator arrives
	 */
	@Override
	public void handleReplicatorArrive(Direction dir, Replicator replicator) {
		ProtoLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		replicator.setPosition(this);
		this.replicator = replicator;
	}

	/*
	 * A replicator leaves
	 */
	@Override
	public void handleReplicatorLeave(Replicator replicator) {
		this.replicator = null;
	};

	/*
	 * Player put down a box.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		ProtoLogger.log("Sikeres dobozletétel a(z) " + this.toString() + " mezőre");

		box.setPosition(this);

		boxes.push(box);
	}

	/*
	 * Neither does anything.
	 */
	@Override
	public void handleBoxPickUp(Box box) {
		ProtoLogger.log("Sikeres dobozfelvétel a(z) " + this.toString() + " mezőről");

		box.setPosition(null);

		if (!boxes.remove(box)) {
			ProtoLogger.logError("Trying to remove a box from a field that does not contain the box");
		}
	}
}
