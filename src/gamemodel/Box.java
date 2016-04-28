package gamemodel;

import debug.ProtoLogger;
import gamemodel.events.ModelEventSource;

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
		ModelEventSource.notifyBoxPutDown(this, element);

		element.handleBoxPutDown(dir, this);
	}

	/*
	 * The box leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		ModelEventSource.notifyBoxPickedUp(this, element);

		element.handleBoxPickUp(this);
	}

	/*
	 * Set the position without notifying the observers
	 */
	@Override
	public void setPosition(MapElement position) {
		MapElement formerPosition = this.position;

		super.setPosition(position);

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
