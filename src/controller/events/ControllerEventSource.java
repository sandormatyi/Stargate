package controller.events;

import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import debug.UILogger;
import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.Movable;
import gamemodel.Player;
import gamemodel.ProjectileType;
import gamemodel.ZPM;

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
	 * A list of the subscribed IZPMEventListener
	 */
	private static Set<IZPMEventListener> zpmEventListeners = new HashSet<IZPMEventListener>();

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
	public static void subscribe(IZPMEventListener listener) {
		if (listener != null)
			zpmEventListeners.add(listener);
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
		zpmEventListeners.clear();
		gameEventListeners.clear();
	}

	/*
	 * Notifies the observers that a MapElement was created
	 */
	public static void notifyMapElementCreated(final MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMapElementCreated(mapElement);
				}
			});
	}

	/*
	 * Notifies the observers that a MapElement was removed
	 */
	public static void notifyMapElementRemoved(final MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMapElementRemoved(mapElement);
				}
			});
	}

	/*
	 * Notifies the observers that a Stargate was opened
	 */
	public static void notifyStargateOpened(final MapElement mapElement, final ProjectileType type,
			final Direction direction) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onStargateOpened(mapElement, type, direction);
				}
			});
	}

	/*
	 * Notifies the observers that a Door was opened or closed
	 */
	public static void notifyDoorStateChanged(final Door door) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onDoorStateChanged(door);
				}
			});
	}

	/*
	 * Notifies the observers that a Movable has been changed
	 */
	public static void notifyMovableChanged(final Movable movable) {
		assertIsOnUIThread();

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
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onMovableDestroyed(movable);
				}
			});
	}

	/*
	 * Notifies the observers that a box has been put down
	 */
	public static void notifyBoxPutDown(final Box box, final MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onBoxPutDown(box, mapElement);
				}
			});
	}

	/*
	 * Notifies the observers that a box has been picked up
	 */
	public static void notifyBoxPickedUp(final Box box, final MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onBoxPickedUp(box, mapElement);
				}
			});
	}

	/*
	 * Notifies the observers that a ZPM has been created
	 */
	public static void notifyZPMCreated(final ZPM zpm, final MapElement position) {
		assertIsOnUIThread();

		for (final IZPMEventListener listener : zpmEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onZPMCreated(zpm, position);
				}
			});
	}

	/*
	 * Notifies the observers that a ZPM has been picked up
	 */
	public static void notifyZPMPickedUp(final Player player, final ZPM zpm) {
		assertIsOnUIThread();

		for (final IZPMEventListener listener : zpmEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onZPMPickedUp(player, zpm);
				}
			});
	}

	/*
	 * Notifies the observers that the game is over
	 */
	public static void notifyGameOver(final Player winner) {
		assertIsOnUIThread();

		for (final IGameEventListener listener : gameEventListeners)
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					listener.onGameOver(winner);
				}
			});
	}

	public static void assertIsOnUIThread() {
		if (!SwingUtilities.isEventDispatchThread())
			UILogger.log("Function was not called from UI thread!");
	}
}
