package gamemodel.events;

import java.util.HashSet;
import java.util.Set;

import gamemodel.Player;
import gamemodel.Projectile;
import gamemodel.Replicator;
import gamemodel.ZPM;

public class ModelEventSource {

	/*
	 * A list of the subscribed IZpmPickedUpListeners
	 */
	private static Set<IZPMPickedUpListener> zpmListeners = new HashSet<IZPMPickedUpListener>();

	/*
	 * A list of the subscribed IProjectileCreatedListeners
	 */
	private static Set<IProjectileStateListener> projectileListeners = new HashSet<IProjectileStateListener>();

	/*
	 * A list of the subscribed IReplicatorDestroyedListeners
	 */
	private static Set<IReplicatorDestroyedListener> replicatorListeners = new HashSet<IReplicatorDestroyedListener>();

	/*
	 * Add a new IZpmPickedUpListener
	 */
	public static void subscribe(IZPMPickedUpListener listener) {
		zpmListeners.add(listener);
	}

	/*
	 * Add a new IReplicatorDestroyedListener
	 */
	public static void subscribe(IReplicatorDestroyedListener listener) {
		replicatorListeners.add(listener);
	}

	/*
	 * Add a new IProjectileCreatedListener
	 */
	public static void subscribe(IProjectileStateListener listener) {
		projectileListeners.add(listener);
	}

	/*
	 * Unsubscribe all observers
	 */
	public static void clear() {
		projectileListeners.clear();
		zpmListeners.clear();
		replicatorListeners.clear();
	}

	/*
	 * Notifies the listeners that a ZPM has been picked up
	 */
	public static void notifyZPMPickedUp(Player player, ZPM zpm) {
		for (IZPMPickedUpListener listener : zpmListeners)
			listener.onZPMPickedUp(player, zpm);
	}

	/*
	 * Notifies the listeners that a Projectile has been created
	 */
	public static void notifyProjectileCreated(Projectile projectile) {
		for (IProjectileStateListener listener : projectileListeners)
			listener.onProjectileCreated(projectile);
	}

	/*
	 * Notifies the listeners that a Projectile has been destroyed
	 */
	public static void notifyProjectileDestroyed(Projectile projectile) {
		for (IProjectileStateListener listener : projectileListeners)
			listener.onProjectileDestroyed(projectile);
	}

	/*
	 * Notifies the listeners that a Replicator has been destroyed
	 */
	public static void notifyReplicatorDestroyed(Replicator replicator) {
		for (IReplicatorDestroyedListener listener : replicatorListeners)
			listener.onReplicatorDestroyed(replicator);
	}
}
