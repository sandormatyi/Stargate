package controller;

import java.util.HashSet;

import gamemodel.Direction;
import gamemodel.Player;
import gamemodel.Projectile;
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
	 * The object representing the player character of the game
	 */
	private Player player;

	/*
	 * The set of ZPMs located on the map
	 */
	private HashSet<ZPM> zpmSet;

	/*
	 * Stores the current state of the Projectile
	 */
	private boolean isProjectileMoving = false;

	/*
	 * Default constructor
	 */
	public Controller(Game game, Player player, HashSet<ZPM> zpmSet) {
		this.game = game;
		this.player = player;
		this.zpmSet = zpmSet;

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
	public void moveOrTurnPlayer(Direction dir) {
		if (player.getDirection() == dir) {
			player.move();
		} else {
			player.turn(dir);
		}

		// TODO: Check both players
		if (!player.isAlive()) {
			game.stop(player, false);
		}
	}

	/*
	 * Make the player shoot a projectile of the first type
	 */
	public void shootFirst() {
		player.shootFirst();
	}

	/*
	 * Make the player shoot a projectile of the second type
	 */
	public void shootSecond() {
		player.shootSecond();
	}

	/*
	 * Make the player pick up a box
	 */
	public void pickUpBox() {
		player.pickUpBox();
	}

	/*
	 * Make the player put down a box
	 */
	public void putDownBox() {
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