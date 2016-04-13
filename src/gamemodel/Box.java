package gamemodel;

import debug.ProtoLogger;

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
		element.handleBoxPutDown(dir, this);
	}

	/*
	 * The box leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		element.handleBoxPickUp(this);
	}

	/*
	 * Box has been destroyed. Set back the location to the init position.
	 */
	public void respawn() {
		leaveMapElement(position);

		ProtoLogger.log("Egy doboz létrejön a(z) " + initPosition.toString() + " mezőn");

		arriveOnMapElement(null, initPosition);
	}
}
