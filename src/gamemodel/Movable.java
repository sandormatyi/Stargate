package gamemodel;

import debug.SkeletonLogger;

public abstract class Movable {
	/*
	 * Moveable's position
	 */
	protected MapElement position;

	public Movable(MapElement position) {
		this.position = position;
	}

	/*
	 * Set the position
	 */
	public void setPosition(MapElement position) {
		SkeletonLogger.functionCalled(this, "setPosition", new Object[] { position });

		this.position = position;

		SkeletonLogger.returnFromFunction(null);
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
		// throw new NotImplementedException();
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
