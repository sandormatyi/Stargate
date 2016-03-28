package gamemodel.events;

import gamemodel.Projectile;

public interface IProjectileStateListener {

	/*
	 * Handles the creation of a Projectile
	 */
	void onProjectileCreated(Projectile projectile);

	/*
	 * Handles the destruction of a Projectile
	 */
	void onProjectileDestroyed(Projectile projectile);
}
