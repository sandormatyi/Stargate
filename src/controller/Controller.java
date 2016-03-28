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

		if (!player.isAlive())
			game.stop(false);
	}

	/*
	 * Make the player shoot a projectile
	 */
	public void shoot() {
		player.shoot();
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

	@Override
	public void onZPMPickedUp(ZPM zpm) {
		game.incrementScore();

		zpmSet.remove(zpm);

		if (zpmSet.isEmpty())
			game.stop(true);
	}

	@Override
	public void onProjectileCreated(Projectile projectile) {
		isProjectileMoving = true;

		while (isProjectileMoving)
			projectile.move();
	}

	@Override
	public void onProjectileDestroyed(Projectile projectile) {
		isProjectileMoving = false;
	}
}