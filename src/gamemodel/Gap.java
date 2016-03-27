package gamemodel;

public class Gap extends MapElement {

	/*
	 * Player arrives and character dies.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		player.setPosition(this);
		player.die();
	}

	/*
	 * Projectile simply goes through.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
		projectile.move();
	}

	/*
	 * Box gets destroyed in the gap, then immediately respawns.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		box.setPosition(this);
		box.respawn();
	}
}
