package gamemodel;

import controller.MapHelper;
import debug.GameLogger;

public class Gap extends MapElement {

	/*
	 * Constructor inherited from base class
	 */
	public Gap(Coord coord) {
		super(coord);
	}

	/*
	 * Player arrives and character dies.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		GameLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		player.setPosition(this);
		player.die();
	}

	/*
	 * Projectile simply goes through.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
	}

	/*
	 * A replicator arrives
	 */
	@Override
	public void handleReplicatorArrive(Direction dir, Replicator replicator) {
		GameLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		replicator.setPosition(this);
		replicator.destroy();
		MapHelper.replaceWithRoad(this);
		GameLogger.log("A(z) " + this.toString() + " mező Road mezővé alakult");
	}

	/*
	 * Box gets destroyed in the gap, then immediately respawns.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		GameLogger.log("Sikeres dobozletétel a(z) " + this.toString() + " mezőre");
		GameLogger.log("Egy doboz elpusztult a(z) " + this.toString() + " mezőn");

		box.setPosition(this);
		box.respawn();
	}

	@Override
	public void handleBoxPickUp(Box box) {
		box.setPosition(null);
	}
}
