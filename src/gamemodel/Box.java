package gamemodel;

import debug.SkeletonLogger;

public class Box extends Movable {
	/*
	 * Starting position If the box is destroyed then its position will be set
	 * back to this
	 */
	private MapElement initPosition;

	/*
	 * Constructor using position
	 */
	public Box(MapElement position) {
		super(position);
		this.initPosition = position;
	}

	/*
	 * The box arrives on a mapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		// Signal that the arriveOnMapElement method was called
		SkeletonLogger.functionCalled(this, "arriveOnMapElement", new Object[] { element });
		// Put Down box
		element.handleBoxPutDown(dir, this);
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * The box leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		// Signal that the leaveMapElement method was called
		SkeletonLogger.functionCalled(this, "leaveMapElement", new Object[] { element });
		// Pick up box
		element.handleBoxPickUp(this);
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Box has been destroyed. Set back the location to the init position.
	 */
	public void respawn() {
		// Signal that the respawn method was called
		SkeletonLogger.functionCalled(this, "respawn", null);
		// Leave Current MapElement
		leaveMapElement(position);
		// Reinitialize to the starting position
		arriveOnMapElement(null, initPosition);
		// Signal that the method returned
		SkeletonLogger.returnFromFunction(null);
	}
}
