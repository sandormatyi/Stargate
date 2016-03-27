package gamemodel;

public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	Direction getOppositeDirection(Direction dir) {
		switch (dir) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		default:
			return null;
		}
	}
}
