package gamemodel;

public class Box extends Movable {
	/*
	 * Starting position
	 * If the box is destroyed then its position will be set back to this
	 */
	private MapElement initPosition;

	public Box(MapElement position, MapElement initPosition) {
		super(position);
		this.initPosition = initPosition;
	}

	@Override
	/*
	 * The box arrives on a mapElement
	 */
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handleBoxPutDown(dir, this);
	}

	@Override
	/*
	 * The box leaves a mapElement
	 */
	public void leaveMapElement(MapElement element) {
		//Call the next Mapelement's Box Pick up handle method
		element.handleBoxPickUp();
	}
	
	/*
	 * Box has been destroyed. Set back the location to the init position.
	 */
	public void respawn() {
		super.position = initPosition;
	}
}
