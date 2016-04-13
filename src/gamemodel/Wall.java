package gamemodel;

public class Wall extends MapElement {

	/*
	 * Constructor inherited from base class
	 */
	public Wall(String coord) {
		super(coord);
	}

	/*
	 * Projectile arrives on this tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
		projectile.destroy();
	}
}
