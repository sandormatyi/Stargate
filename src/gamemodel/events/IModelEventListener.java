package gamemodel.events;

import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.Movable;
import gamemodel.Player;
import gamemodel.Projectile;
import gamemodel.ProjectileType;
import gamemodel.Replicator;
import gamemodel.ZPM;

public interface IModelEventListener {
	/*
	 * Called when the state of a movable is changed (after moving or turning)
	 */
	void onMovableChanged(Movable movable);

	/*
	 * Called when a box is picked up
	 */
	void onBoxPickedUp(Box box, MapElement mapElement);

	/*
	 * Called when a box is put down
	 */
	void onBoxPutDown(Box box, MapElement mapElement);

	/*
	 * Called when a projectile is created
	 */
	void onProjectileCreated(Projectile projectile);

	/*
	 * Called when a projectile is destroyed
	 */
	void onProjectileDestroyed(Projectile projectile);

	/*
	 * Called when a replicator is destroyed
	 */
	void onReplicatorDestroyed(Replicator replicator);

	/*
	 * Called when a stargate is created
	 */
	void onStargateOpened(MapElement mapElement, ProjectileType type, Direction direction);

	/*
	 * Called when a ZPM is picked up
	 */
	void onZPMPickedUp(Player player, ZPM zpm);

	/*
	 * Called when a door is opened or closed
	 */
	void onDoorStateChanged(Door door);
}
