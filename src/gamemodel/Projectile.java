package gamemodel;

import debug.GameLogger;
import gamemodel.events.ModelEventSource;

public class Projectile extends Movable {

	private Direction direction;
	private ProjectileType type;
	private boolean isAlive = true;

	public Projectile(MapElement position, Direction direction, ProjectileType type) {
		super(position);
		this.direction = direction;
		this.type = type;
	}

	/*
	 * The Projectile arrives on a mapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handleProjectileArrive(dir, this);
	}

	/*
	 * The Projectile leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		// Do nothing
	}

	/*
	 * Get the direction of the projectile
	 */
	@Override
	public Direction getDirection() {
		return direction;
	}

	/*
	 * Returns if the projectile is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/*
	 * Move the projectile to the next MapElement
	 */
	@Override
	public void move() {
		this.leaveMapElement(position);

		MapElement nextposition = position.getNeighbour(direction);

		if (nextposition != null) {
			arriveOnMapElement(direction, nextposition);
		} else {
			GameLogger.logError("Projectile tried to move on a MapElement that does not exist");
			destroy();
		}
	}

	/*
	 * Set the position and notify the observers
	 */
	@Override
	public void setPosition(MapElement position) {
		super.setPosition(position);

		ModelEventSource.notifyMovableChanged(this);
	}

	/*
	 * Open a stargate
	 */
	public Stargate openStargate() {
		Direction oppositeDirection = Direction.getOppositeDirection(direction);

		GameLogger.log(this.toString() + " csillagkaput nyitott " + position.toString() + " mező "
				+ oppositeDirection.toString() + " oldalán");

		Stargate stargate = Stargate.createStargate(position, type, oppositeDirection);

		return stargate;
	}

	/*
	 * Destroy the projectile
	 */
	public void destroy() {
		GameLogger.log(this.toString() + " lövedék megsemmisült a(z) " + position.toString() + " mezőn");

		isAlive = false;

		ModelEventSource.notifyProjectileDestroyed(this);
	}

	/*
	 * Returns the relative path of the image representing the object
	 */
	@Override
	public String getImagePath() {
		return super.getImagePath() + "_" + type.name().toLowerCase();
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return type.toString();
	}
}
