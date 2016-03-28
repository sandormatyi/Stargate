package gamemodel.events;

import java.util.HashSet;
import java.util.Set;

import gamemodel.Projectile;
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
	 * Add a new IZpmPickedUpListener
	 */
	public static void subscribe(IZPMPickedUpListener listener) {
		zpmListeners.add(listener);
	}

	/*
	 * Add a new IProjectileCreatedListener
	 */
	public static void subscribe(IProjectileStateListener listener) {
		projectileListeners.add(listener);
	}

	/*
	 * Remove an IZpmPickedUpListener
	 */
	public static void unsubscribe(IZPMPickedUpListener listener) {
		zpmListeners.remove(listener);
	}

	/*
	 * Remove an IProjectileCreatedListener
	 */
	public static void unsubscribe(IProjectileStateListener listener) {
		projectileListeners.remove(listener);
	}

	/*
	 * Unsubscribe all observers
	 */
	public static void clear() {
		projectileListeners.clear();
		zpmListeners.clear();
	}

	/*
	 * Notifies the listeners that a ZPM has been picked up
	 */
	public static void notifyZPMPickedUp(ZPM zpm) {
		for (IZPMPickedUpListener listener : zpmListeners)
			listener.onZPMPickedUp(zpm);
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
}
