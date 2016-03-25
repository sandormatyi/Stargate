package gamemodel;

import java.util.HashMap;

public abstract class MapElement {

	private HashMap<Direction, MapElement> neighbours = new HashMap<Direction, MapElement>();
	private Box box = null;

	public MapElement getNeighbour(Direction dir) {
		return neighbours.get(dir);
	}

	public void setNeighbour(Direction dir, MapElement neighbour) {
		neighbours.put(dir, neighbour);
	}

	public Box getBox() {
		// na talán most
		return box;
	}

	public void handlePlayerArrive(Direction dir, Player player) {
		// TODO
	}

	public void handlePlayerLeave() {
		// TODO
	}

	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		// TODO
	}

	public void handleBoxPutDown(Direction dir, Box box) {
		// TODO
	}

	public void handleBoxPickUp() {
		// TODO
	}
}
