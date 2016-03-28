package gamemodel;

import debug.SkeletonLogger;

public class Wall extends MapElement {

	/*
	 * Projectile arrives on this tile.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);
		projectile.destroy();

		SkeletonLogger.returnFromFunction(null);
	}
}
