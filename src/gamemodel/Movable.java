package gamemodel;

public abstract class Movable {
	/*
	 *Moveable's position
	*/	
	protected MapElement position;
	
	public Movable(MapElement position) {
		this.position = position;
	}

	/*
	 *Set the position
	*/
	public void setPosition(MapElement position) {
		this.position = position;
	}
	
	/*
	 *Override in the descendants
	*/
	public abstract void arriveOnMapElement(Direction dir, MapElement element);
	
	/*
	 *Override in the descendants
	*/
	public abstract void leaveMapElement(MapElement element);

	/*
	 *Override in the descendants
	*/
	public void move() {
	}
}
