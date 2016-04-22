package gamemodel;

public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	/*
	 * Get a direction's opposite direction
	 */
	static Direction getOppositeDirection(Direction dir) {
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

	/*
	 * Returns the enum object whose name is equal to the given string (case
	 * insensitive)
	 */
	public static Direction fromString(String text) {
		if (text != null) {
			for (Direction d : Direction.values()) {
				if (text.equalsIgnoreCase(d.name())) {
					return d;
				}
			}
		}
		return null;
	}
}
