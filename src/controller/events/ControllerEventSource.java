package controller.events;

import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import gamemodel.MapElement;
import gamemodel.Movable;

public class ControllerEventSource {

	/*
	 * A list of the subscribed IMapEventListeners
	 */
	private static Set<IMapEventListener> mapEventListeners = new HashSet<IMapEventListener>();

	/*
	 * A list of the subscribed IMovableEventListeners
	 */
	private static Set<IMovableEventListener> movableEventListeners = new HashSet<IMovableEventListener>();

	/*
	 * A list of the subscribed IGameEventListeners
	 */
	private static Set<IGameEventListener> gameEventListeners = new HashSet<IGameEventListener>();

	/*
	 * Subscribe a listener
	 */
	public static void subscribe(IMapEventListener listener) {
		if (listener != null)
			mapEventListeners.add(listener);
	}

	/*
	 * Subscribe a listener
	 */
	public static void subscribe(IMovableEventListener listener) {
		if (listener != null)
			movableEventListeners.add(listener);
	}

	/*
	 * Subscribe a listener
	 */
	public static void subscribe(IGameEventListener listener) {
		if (listener != null)
			gameEventListeners.add(listener);
	}

	/*
	 * Clear the sets of the listeners
	 */
	public static void clear() {
		mapEventListeners.clear();
		movableEventListeners.clear();
		gameEventListeners.clear();
	}

	/*
	 * Notifies the observers that a MapElement was created
	 */
	public static void notifyMapElementCreated(final MapElement mapElement) {
		for (final IMapEventListener listener : mapEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMapElementCreated(mapElement);
				}
			});
	}

	/*
	 * Notifies the observers that a Movable has been changed
	 */
	public static void notifyMovableChanged(final Movable movable) {
		for (final IMovableEventListener listener : movableEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMovableChanged(movable);
				}
			});
	}

	/*
	 * Notifies the observers that a Movable has been destroyed
	 */
	public static void notifyMovableDestroyed(final Movable movable) {
		for (final IMovableEventListener listener : movableEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMovableDestroyed(movable);
				}
			});
	}
}
