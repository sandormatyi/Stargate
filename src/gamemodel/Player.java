package gamemodel;

import debug.ProtoLogger;
import gamemodel.events.ModelEventSource;

public abstract class Player extends Movable {

	private Direction direction = Direction.NORTH;
	private boolean isAlive = true;
	private Box box = null;
	protected ProjectileType[] projTypes;

	public Player(MapElement position) {
		super(position);
	}

	/*
	 * Player arrives on a MapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handlePlayerArrive(dir, this);
	}

	/*
	 * Player leaves a MapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		element.handlePlayerLeave(this);
	}

	/*
	 * Player moves
	 */
	@Override
	public void move() {
		MapElement nextPosition = position.getNeighbour(direction);

		// TODO: Remove before upload - for debug purposes only
		if (nextPosition == null) {
			ProtoLogger.logError("Player tried to step on a MapElement that does not exist");
			arriveOnMapElement(Direction.getOppositeDirection(direction), position);
		}

		ProtoLogger.logCommand(this.toString() + " a(z) " + position.toString() + " mezőről a(z) "
				+ nextPosition.toString() + " mezőre próbál lépni");

		this.leaveMapElement(position);

		arriveOnMapElement(direction, nextPosition);
	}

	/*
	 * Player is alive?
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/*
	 * Get the direction of the player
	 */
	public Direction getDirection() {
		return direction;
	}

	/*
	 * Turn the player to a direction
	 */
	public void turn(Direction direction) {
		ProtoLogger.logCommand(this.toString() + " elfordult " + direction.toString() + " irányba");

		this.direction = direction;
	}

	/*
	 * Shoot a projectile of the first type
	 */
	public void shootFirst() {
		Projectile projectile = new Projectile(position, direction, projTypes[0]);

		ProtoLogger.logCommand(
				this.toString() + " " + projectile.toString() + " lövedéket lőtt " + direction.toString() + " irányba");

		// Notify the listeners that a projectile has been created
		ModelEventSource.notifyProjectileCreated(projectile);
	}

	/*
	 * Shoot a projectile of the second type
	 */
	public void shootSecond() {
		Projectile projectile = new Projectile(position, direction, projTypes[1]);

		ProtoLogger.logCommand(
				this.toString() + " " + projectile.toString() + " lövedéket lőtt " + direction.toString() + " irányba");

		// Notify the listeners that a projectile has been created
		ModelEventSource.notifyProjectileCreated(projectile);
	}

	/*
	 * Pick up a box from the next mapElement
	 */
	public void pickUpBox() {
		if (box == null) {
			MapElement nextPosition = position.getNeighbour(direction);

			if (nextPosition != null) {
				ProtoLogger.logCommand(this.toString() + " megpróbál felvenni egy dobozt a(z) "
						+ nextPosition.toString() + " mezőről");

				box = nextPosition.getBox(direction);

				if (box != null) {
					box.leaveMapElement(box.position);
					position.incrementWeight();
				}
			}
		}
	}

	/*
	 * Put down a box to the next mapElement
	 */
	public void putDownBox() {
		if (box != null) {
			MapElement nextPosition = position.getNeighbour(direction);

			if (nextPosition != null) {
				ProtoLogger.logCommand(
						this.toString() + " megpróbál letenni egy dobozt a(z) " + nextPosition.toString() + " mezőre");

				box.arriveOnMapElement(direction, nextPosition);
				position.decrementWeight();
				box = null;
			}
		}
	}

	/*
	 * Set the isAlive to false
	 */
	public void die() {
		ProtoLogger.log(this.toString() + " meghalt a(z) " + position.toString() + " mezőn");

		isAlive = false;
	}

	public int getWeight() {
		if (box != null) {
			return 3;
		} else
			return 2;
	}
}
