package gamemodel;

import debug.ProtoLogger;
import gamemodel.events.ModelEventSource;

public class Projectile extends Movable {

	private Direction direction;
	private ProjectileType type;

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
	 * Move the projectile to the next MapElement
	 */
	@Override
	public void move() {
		this.leaveMapElement(position);

		MapElement nextposition = position.getNeighbour(direction);

		if (nextposition != null) {
			arriveOnMapElement(direction, nextposition);
		} else {
			ProtoLogger.logError("Projectile tried to move on a MapElement that does not exist");
			destroy();
		}
	}

	/*
	 * Open a stargate
	 */
	public Stargate openStargate() {
		Direction oppositeDirection = Direction.getOppositeDirection(direction);

		ProtoLogger.log(this.toString() + " csillagkaput nyitott " + position.toString() + " mező "
				+ oppositeDirection.toString() + " oldalán");

		Stargate stargate = Stargate.createStargate(position, type, oppositeDirection);

		return stargate;
	}

	/*
	 * Destroy the projectile
	 */
	public void destroy() {
		ProtoLogger.log(this.toString() + " lövedék megsemmisült a(z) " + position.toString() + " mezőn");

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
