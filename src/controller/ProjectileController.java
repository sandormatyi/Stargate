package controller;

import gamemodel.Projectile;
import gamemodel.events.IProjectileStateListener;
import gamemodel.events.ModelEventSource;

public class ProjectileController implements IProjectileStateListener {

	/*
	 * Stores the current state of the Projectile
	 */
	boolean isMoving = false;

	public ProjectileController() {
		ModelEventSource.subscribe(this);
	}

	@Override
	public void onProjectileCreated(Projectile projectile) {
		isMoving = true;

		while (isMoving)
			projectile.move();
	}

	@Override
	public void onProjectileDestroyed(Projectile projectile) {
		isMoving = false;
	}

}
