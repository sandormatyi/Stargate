package gamemodel;

import java.util.HashMap;

public abstract class MapElement {

	private HashMap<Direction, MapElement> neighbours = new HashMap<Direction, MapElement>();
	private Box box = null;

	/*
	 * Getter for neighbour MapElement in a specific direction.
	 */
	public MapElement getNeighbour(Direction dir) {
		return neighbours.get(dir);
	}

	/*
	 * Setter for neighbour MapElement in a specific direction.
	 */
	public void setNeighbour(Direction dir, MapElement neighbour) {
		neighbours.put(dir, neighbour);
	}

	/*
	 * Getter for the box, that is on this MapElement, if any.
	 */
	public Box getBox() {
		return box;
	}

	/*
	 * Funcion for player arrival. Puts him back to the previous tile by
	 * default.
	 */
	public void handlePlayerArrive(Direction dir, Player player) {
		player.leaveMapElement(this.getNeighbour(dir.getOppositeDirection(dir)));
	}

	/*
	 * Funcion for player leave. Does nothing by default
	 */
	public void handlePlayerLeave() {

	}

	/*
	 * Funcion for projectile arrival. Does nothing by default
	 */
	public void handleProjectileArrive(Direction dir, Projectile projectile) {

	}

	/*
	 * Function for box putting down on this tile. Takes it back to the previous
	 * tile by default.
	 */
	public void handleBoxPutDown(Direction dir, Box box) {
		box.leaveMapElement(this.getNeighbour(dir.getOppositeDirection(dir)));
	}

	/*
	 * Function for box picking up from this tile. Does nothing by default.
	 */
	public void handleBoxPickUp() {

	}
}
