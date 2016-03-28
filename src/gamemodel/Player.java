package gamemodel;

import debug.SkeletonLogger;
import gamemodel.events.ModelEventSource;

public class Player extends Movable {

	private Direction direction;
	private boolean isAlive = true;
	private ProjectileType projType = ProjectileType.YELLOW;
	private Box box = null;

	public Player(MapElement position, Direction direction) {
		super(position);
		this.direction = direction;
	}

	/*
	 * Player arrives on a MapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		SkeletonLogger.functionCalled(this, "arriveOnMapElement", new Object[] { dir, element });

		element.handlePlayerArrive(dir, this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player leaves a MapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		SkeletonLogger.functionCalled(this, "leaveMapElement", new Object[] { element });

		element.handlePlayerLeave();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player moves
	 */
	@Override
	public void move() {
		SkeletonLogger.functionCalled(this, "move", null);

		this.leaveMapElement(position);

		MapElement nextposition = position.getNeighbour(direction);

		if (nextposition != null) {
			arriveOnMapElement(direction, nextposition);
		} else {
			System.err.println("Player tried to step on a MapElement that does not exist!");
			arriveOnMapElement(Direction.getOppositeDirection(direction), position);
		}

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Player is alive?
	 */
	public boolean isAlive() {
		SkeletonLogger.functionCalled(this, "isAlive", null);

		SkeletonLogger.returnFromFunction(isAlive);
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
		SkeletonLogger.functionCalled(this, "turn", new Object[] { direction });

		this.direction = direction;

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Shoot a projectile
	 */
	public void shoot() {
		SkeletonLogger.functionCalled(this, "shoot", null);

		Projectile projectile = new Projectile(position, direction, projType);

		// Notify the listeners that a projectile has been created
		ModelEventSource.notifyProjectileCreated(projectile);

		if (projType == ProjectileType.BLUE) {
			projType = ProjectileType.YELLOW;
		} else {
			projType = ProjectileType.BLUE;
		}

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Pick up a box from the next mapElement
	 */
	public void pickUpBox() {
		if (this.box != null)
			return;

		MapElement nextPosition = position.getNeighbour(direction);
		if (nextPosition == null)
			return;

		this.box = nextPosition.getBox(direction);
		if (this.box != null)
			box.leaveMapElement(nextPosition);
	}

	/*
	 * Put down a box to the next mapElement
	 */
	public void putDownBox() {
		MapElement nextPosition = position.getNeighbour(direction);
		if (nextPosition == null)
			return;

		this.box.arriveOnMapElement(direction, nextPosition);
		this.box = null;
	}

	/*
	 * Set the isAlive to false
	 */
	public void die() {
		this.isAlive = false;
	}
}
