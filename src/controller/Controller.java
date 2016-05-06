package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.SwingUtilities;

import controller.events.ControllerEventSource;
import debug.GameLogger;
import debug.RandomGenerator;
import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.Movable;
import gamemodel.Player;
import gamemodel.Projectile;
import gamemodel.ProjectileType;
import gamemodel.Replicator;
import gamemodel.Road;
import gamemodel.Stargate;
import gamemodel.ZPM;
import gamemodel.events.IModelEventListener;
import gamemodel.events.ModelEventSource;
import userinterface.UIUtility;

public class Controller implements IModelEventListener {

	/*
	 * The object representing the current game
	 */
	private Game game;

	/*
	 * The set of ZPMs located on the map
	 */
	private HashSet<ZPM> zpmSet;

	/*
	 * The object representing the player characters of the game
	 */
	private HashMap<PlayerType, Player> players = new HashMap<PlayerType, Player>();

	/*
	 * The object representing the replicator
	 */
	private Replicator replicator;

	/*
	 * Stores the current state of the replicator
	 */
	private volatile boolean isReplicatorMoving = false;

	/*
	 * Default constructor
	 */
	public Controller(Game game, HashSet<ZPM> zpmSet, Player oneill, Player jaffa, Replicator replicator) {
		this.game = game;
		this.zpmSet = zpmSet;
		this.replicator = replicator;

		players.put(PlayerType.ONeill, oneill);
		players.put(PlayerType.Jaffa, jaffa);

		ModelEventSource.subscribe(this);
	}

	/*
	 * Returns a list of references of the existing players
	 */
	public List<Player> getPlayers() {
		List<Player> playerList = new ArrayList<Player>();

		for (Player p : players.values()) {
			if (p != null)
				playerList.add(p);
		}

		return playerList;
	}

	/*
	 * Cleans up the resources after a game
	 */
	public void gameOver() {
		// Clean up the model layer
		ModelEventSource.clear();
		Stargate.clearStargates();

		//
		isReplicatorMoving = false;
		players.clear();
		replicator = null;
		zpmSet.clear();
	}

	/*
	 * Move or turn the player depending on the player's current direction and
	 * check if the game is over
	 */
	public void moveOrTurnPlayer(PlayerType playerType, Direction dir) {
		Player player = players.get(playerType);

		if (player == null) {
			GameLogger.logError("Trying to move a player that does not exist");
			return;
		}

		if (player.getDirection() == dir) {
			player.move();
		} else {
			player.turn(dir);
		}

		// Check if the players are still alive
		for (Player p : players.values()) {
			if (p != null && !p.isAlive()) {
				// Send notification that a player has been killed
				ControllerEventSource.notifyMovableDestroyed(p);

				game.stop(false);
				return;
			}
		}
	}

	/*
	 * Move or turn the replicator depending on its current direction
	 */
	public void moveOrTurnReplicator(Direction dir) {
		if (replicator.getDirection() == dir) {
			replicator.move();
		} else {
			replicator.turn(dir);
		}
	}

	/*
	 * Make the player shoot a projectile of the first type
	 */
	public void shootFirst(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			GameLogger.logError("Trying to shoot with a player that does not exist");
			return;
		}

		player.shootFirst();
	}

	/*
	 * Make the player shoot a projectile of the second type
	 */
	public void shootSecond(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			GameLogger.logError("Trying to shoot with a player that does not exist");
			return;
		}

		player.shootSecond();
	}

	/*
	 * Make the player pick up or put down a box
	 */
	public void pickUpOrPutDownBox(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			GameLogger.logError("Trying to pick up a box with a player that does not exist");
			return;
		}

		if (player.getBox() == null) {
			player.pickUpBox();

			// If the player did not pick up a box, return
			if (player.getBox() == null)
				return;

			// Check if the players are still alive
			for (Player p : players.values()) {
				if (p != null && !p.isAlive()) {
					// Send notification that a player has been killed
					ControllerEventSource.notifyMovableDestroyed(p);

					game.stop(false);
					return;
				}
			}
		} else {
			player.putDownBox();
		}

		// Send notification to the listeners
		ControllerEventSource.notifyMovableChanged(player);
	}

	/*
	 * Move the replicator until it dies
	 */
	public void moveReplicatorUntilDeath() {

		// Hand off the moving of the replicator to a background thread
		new Thread(new Runnable() {

			@Override
			public void run() {
				isReplicatorMoving = (replicator != null);

				while (isReplicatorMoving) {
					final Direction dir = Direction.values()[RandomGenerator
							.getRandomNumber(Direction.values().length)];

					// Create the Runnable object that moves the replicator
					Runnable moveReplicator = new Runnable() {
						@Override
						public void run() {
							moveOrTurnReplicator(dir);
						}
					};

					// The background thread needs to post to the event loop
					SwingUtilities.invokeLater(moveReplicator);

					try {
						Thread.sleep(UIUtility.getReplicatorDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// If the replicator is still alive, move it again
					if (isReplicatorMoving)
						SwingUtilities.invokeLater(moveReplicator);

					try {
						Thread.sleep(UIUtility.getReplicatorDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/*
	 * Increment Score and if there are no more ZPMs on the map, stop the game
	 */
	@Override
	public void onZPMPickedUp(Player player, ZPM zpm) {
		game.incrementScore(player);
		zpmSet.remove(zpm);

		PlayerType type = null;
		for (PlayerType t : players.keySet()) {
			if (players.get(t) == player)
				type = t;
		}

		// Send notification that a ZPM has been picked up
		ControllerEventSource.notifyZPMPickedUp(type, zpm);

		// If it was the second ZPM picked up by ONeill, generate a new on on a
		// random Road
		if (player == players.get(PlayerType.ONeill)) {
			if (game.getScore(player) % 2 == 0) {
				Road randomRoad = MapHelper.getRandomRoad();

				while (randomRoad.getZpm() != null)
					randomRoad = MapHelper.getRandomRoad();

				ZPM tempZpm = new ZPM();
				randomRoad.setZpm(tempZpm);
				zpmSet.add(tempZpm);
				GameLogger.log("Egy új ZPM generálódott a(z) " + randomRoad.toString() + " mezőn");

				// Send notification that a ZPM has been created
				ControllerEventSource.notifyZPMCreated(tempZpm, randomRoad);
			}
		}

		if (zpmSet.isEmpty()) {
			game.stop(true);
		}
	}

	/*
	 * Until the projectile is "alive", move it.
	 */
	@Override
	public void onProjectileCreated(final Projectile projectile) {

		// Hand off the moving of the projectile to a background thread
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (projectile.isAlive()) {

					// The background thread needs to post to the event loop
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							if (projectile.isAlive())
								projectile.move();
						}
					});

					try {
						Thread.sleep(UIUtility.getProjectileDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/*
	 * If the Projectile is destroyed, then stop its moving
	 */
	@Override
	public void onProjectileDestroyed(Projectile projectile) {
		// Send notification that a movable has been destroyed
		ControllerEventSource.notifyMovableDestroyed(projectile);
	}

	/*
	 * Forwards the notification to the ControllerEventSource observers
	 */
	@Override
	public void onStargateOpened(MapElement mapElement, ProjectileType type, Direction direction) {
		ControllerEventSource.notifyStargateOpened(mapElement, type, direction);
	}

	/*
	 * If a replicator is destroyed, remove the reference to it
	 */
	@Override
	public void onReplicatorDestroyed(Replicator replicator) {
		isReplicatorMoving = false;

		// Send notification that a movable has been destroyed
		ControllerEventSource.notifyMovableDestroyed(replicator);
	}

	/*
	 * Forwards the notification to the ControllerEventSource observers
	 */
	@Override
	public void onMovableChanged(Movable movable) {
		ControllerEventSource.notifyMovableChanged(movable);
	}

	/*
	 * Forwards the notification to the ControllerEventSource observers
	 */
	@Override
	public void onDoorStateChanged(Door door) {
		ControllerEventSource.notifyDoorStateChanged(door);
	}

	/*
	 * Forwards the notification to the ControllerEventSource observers
	 */
	@Override
	public void onBoxPickedUp(Box box, MapElement mapElement) {
		ControllerEventSource.notifyBoxPickedUp(box, mapElement);
	}

	/*
	 * Forwards the notification to the ControllerEventSource observers
	 */
	@Override
	public void onBoxPutDown(Box box, MapElement mapElement) {
		ControllerEventSource.notifyBoxPutDown(box, mapElement);
	}

}