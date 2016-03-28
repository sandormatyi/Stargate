package gamemodel;

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
		element.handleBoxPutDown(dir, this);
	}

	/*
	 * The box leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		element.handleBoxPickUp();
	}

	/*
	 * Box has been destroyed. Set back the location to the init position.
	 */
	public void respawn() {
		leaveMapElement(position);
		arriveOnMapElement(null, initPosition);
	}
}
