package gamemodel;

import gamemodel.events.ModelEventSource;

public abstract class Movable {
	/*
	 * Movable's position
	 */
	protected MapElement position;

	public Movable(MapElement position) {
		this.position = position;
	}

	/*
	 * Get the position
	 */
	public MapElement getPosition() {
		return position;
	}

	/*
	 * Set the position
	 */
	public void setPosition(MapElement position) {
		this.position = position;

		ModelEventSource.notifyMovableChanged(this);
	}

	/*
	 * Get the direction
	 */
	public Direction getDirection() {
		return null;
	}

	/*
	 * Override in the descendants
	 */
	public abstract void arriveOnMapElement(Direction dir, MapElement element);

	/*
	 * Override in the descendants
	 */
	public abstract void leaveMapElement(MapElement element);

	/*
	 * Override in the descendants
	 */
	public void move() {
		// Do nothing
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
