package gamemodel;

import debug.GameLogger;
import gamemodel.events.ModelEventSource;

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
	@Override
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
			GameLogger.logError(this.toString() + " tried to step on a MapElement that does not exist");
			arriveOnMapElement(Direction.getOppositeDirection(direction), position);
		}

		GameLogger.logCommand(this.toString() + " a(z) " + position.toString() + " mezőről a(z) "
				+ nextPosition.toString() + " mezőre próbál lépni");

		this.leaveMapElement(position);

		arriveOnMapElement(direction, nextPosition);
	}

	/*
	 * Turn the replicator to a direction
	 */
	public void turn(Direction direction) {
		GameLogger.logCommand(this.toString() + " elfordult " + direction.toString() + " irányba");

		this.direction = direction;

		ModelEventSource.notifyMovableChanged(this);
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
	 * Destroy the replicator
	 */
	public void destroy() {
		GameLogger.log(this.toString() + " megsemmisült a(z) " + position.toString() + " mezőn");

		leaveMapElement(position);

		ModelEventSource.notifyReplicatorDestroyed(this);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
