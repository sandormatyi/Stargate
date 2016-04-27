package gamemodel;

import java.util.HashMap;
import java.util.Stack;

import debug.ProtoLogger;

public abstract class MapElement {
	private HashMap<Direction, MapElement> neighbours = new HashMap<Direction, MapElement>();
	protected Stack<Box> boxes = new Stack<Box>();
	protected Replicator replicator = null;
	protected Coord coord;

	/*
	 * Constructor that takes the coordinates of the MapElement as a parameter
	 */
	public MapElement(Coord coord) {
		this.coord = coord;
	}

	/*
	 * Returns the coordinate of the MapElement
	 */
	public Coord getCoord() {
		return coord;
	}

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
	public Box getBox(Direction dir) {
		if (boxes.empty())
			return null;

		return boxes.peek();
	}

	/*
	 * Funcion for player arrival. Puts him back to the previous tile by
	 * default.
	 */
	public void handlePlayerArrive(Direction dir, Player player) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		MapElement neighbour = getNeighbour(oppositeDirection);

		ProtoLogger.log("Sikertelen lépés: " + player.toString() + " visszalökődött az előző " + neighbour.toString()
				+ " mezőre");

		neighbour.handlePlayerArrive(oppositeDirection, player);
	}

	/*
	 * Funcion for player leave. Does nothing by default.
	 */
	public void handlePlayerLeave(Player player) {
		// Do nothing
	}

	/*
	 * Funcion for projectile arrival. Does nothing by default
	 */
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		// Do nothing
	}

	/*
	 * Function for replicator arrival
	 */
	public void handleReplicatorArrive(Direction dir, Replicator replicator) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		MapElement neighbour = getNeighbour(oppositeDirection);

		ProtoLogger.log("Sikertelen lépés: " + replicator.toString() + " visszalökődött az előző "
				+ neighbour.toString() + " mezőre");

		neighbour.handleReplicatorArrive(oppositeDirection, replicator);
	}

	/*
	 * Funcion for replicator leave. Does nothing by default.
	 */
	public void handleReplicatorLeave(Replicator replicator) {
		// Do nothing
	}

	/*
	 * Function for box putting down on this tile. Takes it back to the previous
	 * tile by default.
	 */
	public void handleBoxPutDown(Direction dir, Box box) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		MapElement neighbour = getNeighbour(oppositeDirection);

		ProtoLogger.log("Sikertelen dobozletétel: A doboz visszalökődik az előző " + neighbour.toString() + " mezőre");

		neighbour.handleBoxPutDown(oppositeDirection, box);
	}

	/*
	 * Function for box picking up from this tile. Does nothing by default.
	 */
	public void handleBoxPickUp(Box box) {
		// Do nothing
	}

	/*
	 * Increments the weight on this MapElement
	 */
	public void incrementWeight() {
		// Do nothing
	}

	/*
	 * Decrements the weight on this MapElement
	 */
	public void decrementWeight() {
		// Do nothing
	}

	/*
	 * Returns the path of the image file that represents the object
	 */
	public String getImagePath() {
		// TODO: Override in derived classes
		return "images/dummy_mapelement.png";
	}

	/*
	 * Get the coordinate and the type of the MapElement as a string
	 */
	@Override
	public String toString() {
		return coord + " (" + this.getClass().getSimpleName() + ")";
	}
}
