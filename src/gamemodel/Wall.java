package gamemodel;

public class Wall extends MapElement {

	/*
	 * Projectile arrives on this tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
		projectile.destroy();
	}
}
