package gamemodel;

public abstract class Movable {

	public Movable(MapElement position) {
		this.position = position;
	}
//blabla
	protected MapElement position;

	public void setPosition(MapElement position) {
		this.position = position;
	}

	public abstract void arriveOnMapElement(Direction dir, MapElement element);

	public abstract void leaveMapElement(MapElement element);

	public void move() {
		// TODO
		this.leaveMapElement(position);
		MapElement nextposition;
		//nextposition = getNeighbourDirection(direction);
		//arriveOnMapElement(direction,nextposition);
	}
}
