package gamemodel.events;

import gamemodel.Direction;
import gamemodel.MapElement;
import gamemodel.Projectile;
import gamemodel.ProjectileType;

public interface IProjectileStateListener {

	/*
	 * Handles the creation of a Projectile
	 */
	void onProjectileCreated(Projectile projectile);

	/*
	 * Handles the destruction of a Projectile
	 */
	void onProjectileDestroyed(Projectile projectile);

	/*
	 * Handles the opening of a Stargate
	 */
	void onStargateOpened(MapElement mapElement, ProjectileType type, Direction direction);
}
