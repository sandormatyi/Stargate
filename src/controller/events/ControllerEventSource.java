package controller.events;

import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import controller.PlayerType;
import debug.UILogger;
import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.Movable;
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
	public static void notifyMapElementCreated(MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			listener.onMapElementCreated(mapElement);
	}

	/*
	 * Notifies the observers that a MapElement was removed
	 */
	public static void notifyMapElementRemoved(MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			listener.onMapElementRemoved(mapElement);
	}

	/*
	 * Notifies the observers that a Stargate was opened
	 */
	public static void notifyStargateOpened(MapElement mapElement, ProjectileType type, Direction direction) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			listener.onStargateOpened(mapElement, type, direction);
	}

	/*
	 * Notifies the observers that a Door was opened or closed
	 */
	public static void notifyDoorStateChanged(Door door) {
		assertIsOnUIThread();

		for (final IMapEventListener listener : mapEventListeners)
			listener.onDoorStateChanged(door);
	}

	/*
	 * Notifies the observers that a Movable has been changed
	 */
	public static void notifyMovableChanged(Movable movable) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			listener.onMovableChanged(movable);
	}

	/*
	 * Notifies the observers that a Movable has been destroyed
	 */
	public static void notifyMovableDestroyed(Movable movable) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			listener.onMovableDestroyed(movable);
	}

	/*
	 * Notifies the observers that a box has been put down
	 */
	public static void notifyBoxPutDown(Box box, MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			listener.onBoxPutDown(box, mapElement);
	}

	/*
	 * Notifies the observers that a box has been picked up
	 */
	public static void notifyBoxPickedUp(Box box, MapElement mapElement) {
		assertIsOnUIThread();

		for (final IMovableEventListener listener : movableEventListeners)
			listener.onBoxPickedUp(box, mapElement);
	}

	/*
	 * Notifies the observers that a ZPM has been created
	 */
	public static void notifyZPMCreated(ZPM zpm, MapElement position) {
		assertIsOnUIThread();

		for (IZPMEventListener listener : zpmEventListeners)
			listener.onZPMCreated(zpm, position);
	}

	/*
	 * Notifies the observers that a ZPM has been picked up
	 */
	public static void notifyZPMPickedUp(PlayerType player, ZPM zpm) {
		assertIsOnUIThread();

		for (final IZPMEventListener listener : zpmEventListeners)
			listener.onZPMPickedUp(zpm);

		for (IGameEventListener listener : gameEventListeners)
			listener.onScoreIncreased(player);
	}

	/*
	 * Notifies the observers that the game is over
	 */
	public static void notifyGameOver(String winner) {
		assertIsOnUIThread();

		for (IGameEventListener listener : gameEventListeners)
			listener.onGameOver(winner);
	}

	public static void assertIsOnUIThread() {
		if (!SwingUtilities.isEventDispatchThread())
			UILogger.log("Function was not called from UI thread!");
	}
}
