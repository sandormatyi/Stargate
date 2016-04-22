package controller;

import java.util.HashMap;
import java.util.HashSet;

import debug.ProtoLogger;
import gamemodel.Direction;
import gamemodel.Player;
import gamemodel.Projectile;
import gamemodel.Replicator;
import gamemodel.ZPM;
import gamemodel.events.IProjectileStateListener;
import gamemodel.events.IZPMPickedUpListener;
import gamemodel.events.ModelEventSource;

public class Controller implements IZPMPickedUpListener, IProjectileStateListener {

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
	 * Stores the current state of the Projectile
	 */
	private boolean isProjectileMoving = false;

	/*
	 * Default constructor
	 */
	public Controller(Game game, HashSet<ZPM> zpmSet, Player oneill, Player jaffa, Replicator replicator) {
		this.game = game;
		this.zpmSet = zpmSet;
		this.replicator = replicator;

		players.put(PlayerType.ONeill, oneill);
		players.put(PlayerType.Jaffa, jaffa);

		ModelEventSource.subscribe((IZPMPickedUpListener) this);
		ModelEventSource.subscribe((IProjectileStateListener) this);
	}

	public void unsubscribe() {
		ModelEventSource.unsubscribe((IZPMPickedUpListener) this);
		ModelEventSource.unsubscribe((IProjectileStateListener) this);
	}

	/*
	 * Move or turn the player depending on the player's current direction and
	 * check if the game is over
	 */
	public void moveOrTurnPlayer(PlayerType playerType, Direction dir) {
		Player player = players.get(playerType);

		if (player == null) {
			ProtoLogger.logError("Trying to move a player that does not exist");
			return;
		}

		if (player.getDirection() == dir) {
			player.move();
		} else {
			player.turn(dir);
		}

		for (Player p : players.values()) {
			if (p != null && !p.isAlive())
				game.stop(player, false);
		}
	}

	/*
	 * Make the player shoot a projectile of the first type
	 */
	public void shootFirst(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			ProtoLogger.logError("Trying to shoot with a player that does not exist");
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
			ProtoLogger.logError("Trying to shoot with a player that does not exist");
			return;
		}

		player.shootSecond();
	}

	/*
	 * Make the player pick up a box
	 */
	public void pickUpBox(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			ProtoLogger.logError("Trying to pick up a box with a player that does not exist");
			return;
		}

		player.pickUpBox();
	}

	/*
	 * Make the player put down a box
	 */
	public void putDownBox(PlayerType playerType) {
		Player player = players.get(playerType);

		if (player == null) {
			ProtoLogger.logError("Trying to put down a box with a player that does not exist");
			return;
		}

		player.putDownBox();
	}

	/*
	 * Increment Score and if it's the last one, end the game
	 */
	@Override
	public void onZPMPickedUp(ZPM zpm) {
		game.incrementScore();

		zpmSet.remove(zpm);

		if (zpmSet.isEmpty()) {
			game.stop(null, true); // TODO
		}
	}

	/*
	 * Until the projectile is "alive", move it.
	 */
	@Override
	public void onProjectileCreated(Projectile projectile) {
		isProjectileMoving = true;

		while (isProjectileMoving) {
			projectile.move();
		}
	}

	/*
	 * If the Projectile is destroyed, then stop its moving
	 */
	@Override
	public void onProjectileDestroyed(Projectile projectile) {
		isProjectileMoving = false;
	}
}