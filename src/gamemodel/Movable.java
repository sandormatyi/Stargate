package gamemodel;

import gamemodel.events.ModelEventSource;

public abstract class Movable {
	/*
	 * Moveable's position
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
	 * Returns the path of the image file that represents the object
	 */
	public String getImagePath() {
		// TODO: Override in derived classes
		return "images/dummy_movable.png";
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
