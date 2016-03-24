package gamemodel;

public class Box extends Movable {

	private MapElement initPosition;

	public Box(MapElement position, MapElement initPosition) {
		super(position);
		this.initPosition = initPosition;
	}

	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		// TODO
	}

	@Override
	public void leaveMapElement(MapElement element) {
		// TODO
	}

	public void respawn() {
		// TODO
	}
}
