package gamemodel;

import debug.SkeletonLogger;

public class Box extends Movable {
	/*
	 * Starting position If the box is destroyed then its position will be set
	 * back to this
	 */
	private MapElement initPosition;

	public Box(MapElement position) {
		super(position);
		this.initPosition = position;
	}

	/*
	 * The box arrives on a mapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		SkeletonLogger.functionCalled(this, "arriveOnMapElement", new Object[] { element });

		element.handleBoxPutDown(dir, this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * The box leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		SkeletonLogger.functionCalled(this, "leaveMapElement", new Object[] { element });

		element.handleBoxPickUp();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Box has been destroyed. Set back the location to the init position.
	 */
	public void respawn() {
		SkeletonLogger.functionCalled(this, "respawn", null);

		leaveMapElement(position);
		arriveOnMapElement(null, initPosition);

		SkeletonLogger.returnFromFunction(null);
	}
}
