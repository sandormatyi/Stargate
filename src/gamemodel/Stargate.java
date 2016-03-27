package gamemodel;

import java.util.HashMap;

public class Stargate {

	/*
	 * A HashMap to store the stargates indexed by their types
	 */
	private static HashMap<ProjectileType, Stargate> stargates = new HashMap<ProjectileType, Stargate>(2);

	private MapElement position;
	private ProjectileType type;
	private Direction direction;

	public Stargate(MapElement position, ProjectileType type, Direction direction) {
		this.position = position;
		this.type = type;
		this.direction = direction;
	}

	/*
	 * Returns the position of the other Stargate
	 */
	public MapElement getExitPosition() {
		ProjectileType otherType = ProjectileType.getOppositeType(type);
		Stargate exitStargate = stargates.get(otherType);

		if (exitStargate != null)
			return exitStargate.position;

		return null;
	}
}
