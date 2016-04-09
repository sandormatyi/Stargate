package gamemodel;

import java.util.HashMap;

import debug.SkeletonLogger;

public abstract class MapElement {

	private HashMap<Direction, MapElement> neighbours = new HashMap<Direction, MapElement>();
	protected Box box = null;

	/*
	 * Proto Test protected String Coord; void setCoord(String name){ Coord =
	 * name; }
	 */

	/*
	 * Getter for neighbour MapElement in a specific direction.
	 */
	public MapElement getNeighbour(Direction dir) {
		SkeletonLogger.functionCalled(this, "getNeighbour", new Object[] { dir });

		MapElement neighbour = neighbours.get(dir);

		SkeletonLogger.returnFromFunction(neighbour);
		return neighbour;
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
		SkeletonLogger.functionCalled(this, "getBox", new Object[] { dir });

		SkeletonLogger.returnFromFunction(box);
		return box;
	}

	/*
	 * Funcion for player arrival. Puts him back to the previous tile by
	 * default.
	 */
	public void handlePlayerArrive(Direction dir, Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		Direction opdir = Direction.getOppositeDirection(dir);
		this.getNeighbour(opdir).handlePlayerArrive(opdir, player);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Funcion for player leave. Does nothing by default.
	 */
	public void handlePlayerLeave(Player player) {
		SkeletonLogger.functionCalled(this, "handlePlayerLeave", null);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Funcion for projectile arrival. Does nothing by default
	 */
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Function for box putting down on this tile. Takes it back to the previous
	 * tile by default.
	 */
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

		Direction opdir = Direction.getOppositeDirection(dir);
		this.getNeighbour(opdir).handleBoxPutDown(opdir, box);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Function for box picking up from this tile. Does nothing by default.
	 */
	public void handleBoxPickUp(Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPickUp", new Object[] { box });

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
