package gamemodel.events;

import java.util.HashSet;
import java.util.Set;

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

public class ModelEventSource {

	/*
	 * A list of the subscribed IModelEventListeners
	 */
	private static Set<IModelEventListener> modelEventListeners = new HashSet<IModelEventListener>();

	/*
	 * Add a new IModelEventListener
	 */
	public static void subscribe(IModelEventListener listener) {
		modelEventListeners.add(listener);
	}

	/*
	 * Unsubscribe all observers
	 */
	public static void clear() {
		modelEventListeners.clear();
	}

	/*
	 * Notifies the listeners that a ZPM has been picked up
	 */
	public static void notifyZPMPickedUp(Player player, ZPM zpm) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onZPMPickedUp(player, zpm);
	}

	/*
	 * Notifies the listeners that a Projectile has been created
	 */
	public static void notifyProjectileCreated(Projectile projectile) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onProjectileCreated(projectile);
	}

	/*
	 * Notifies the listeners that a Projectile has been destroyed
	 */
	public static void notifyProjectileDestroyed(Projectile projectile) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onProjectileDestroyed(projectile);
	}

	/*
	 * Notifies the listeners that a Stargate has been opened
	 */
	public static void notifyStargateOpened(MapElement mapElement, ProjectileType type, Direction direction) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onStargateOpened(mapElement, type, direction);
	}

	/*
	 * Notifies the listeners that a Replicator has been destroyed
	 */
	public static void notifyReplicatorDestroyed(Replicator replicator) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onReplicatorDestroyed(replicator);
	}

	/*
	 * Notifies the listeners that a Movable has been changed
	 */
	public static void notifyMovableChanged(Movable movable) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onMovableChanged(movable);
	}

	/*
	 * Notifies the listeners that a box has been picked up
	 */
	public static void notifyBoxPickedUp(Box box, MapElement mapElement) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onBoxPickedUp(box, mapElement);
	}

	/*
	 * Notifies the listeners that a box has been put down
	 */
	public static void notifyBoxPutDown(Box box, MapElement mapElement) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onBoxPutDown(box, mapElement);
	}

	/*
	 * Notifies the listeners that the state of a door has been changed
	 */
	public static void notifyDoorStateChanged(Door door) {
		for (IModelEventListener listener : modelEventListeners)
			listener.onDoorStateChanged(door);
	}
}
