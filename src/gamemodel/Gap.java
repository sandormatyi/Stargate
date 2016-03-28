package gamemodel;

import debug.SkeletonLogger;

public class Gap extends MapElement {

	/*
	 * Player arrives and character dies.
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		player.setPosition(this);
		player.die();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Projectile simply goes through.
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Box gets destroyed in the gap, then immediately respawns.
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

		box.setPosition(this);
		box.respawn();

		SkeletonLogger.returnFromFunction(null);
	}
}
