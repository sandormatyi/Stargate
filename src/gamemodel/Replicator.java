package gamemodel;

import debug.ProtoLogger;

public class Replicator extends Movable {
	private Direction direction = Direction.NORTH;

	/*
	 * Constructor inherited from base class
	 */
	public Replicator(MapElement position) {
		super(position);
	}

	/*
	 * Get the direction of the replicator
	 */
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handleReplicatorArrive(dir, this);
	}

	@Override
	public void leaveMapElement(MapElement element) {
		element.handleReplicatorLeave(this);
	}

	/*
	 * Replicator moves
	 */
	@Override
	public void move() {
		MapElement nextPosition = position.getNeighbour(direction);

		// TODO: Remove before upload - for debug purposes only
		if (nextPosition == null) {
			ProtoLogger.logError(this.toString() + " tried to step on a MapElement that does not exist");
			arriveOnMapElement(Direction.getOppositeDirection(direction), position);
		}

		ProtoLogger.logCommand(this.toString() + " a(z) " + position.toString() + " mezőről a(z) "
				+ nextPosition.toString() + " mezőre próbál lépni");

		this.leaveMapElement(position);

		arriveOnMapElement(direction, nextPosition);
	}

	/*
	 * Turn the replicator to a direction
	 */
	public void turn(Direction direction) {
		ProtoLogger.logCommand(this.toString() + " elfordult " + direction.toString() + " irányba");

		this.direction = direction;
	}

	/*
	 * Destroy the replicator
	 */
	public void destroy() {
		ProtoLogger.log(this.toString() + " megsemmisült a(z) " + position.toString() + " mezőn");

		// TODO ?
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
