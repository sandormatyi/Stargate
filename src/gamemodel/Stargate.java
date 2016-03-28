package gamemodel;

import java.util.HashMap;

import debug.SkeletonLogger;

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
		SkeletonLogger.functionCalled(this, "getExitPosition", null);

		ProjectileType otherType = ProjectileType.getOppositeType(type);
		Stargate exitStargate = stargates.get(otherType);

		if (exitStargate != null) {
			SkeletonLogger.returnFromFunction(exitStargate.position);
			return exitStargate.position;
		}

		SkeletonLogger.returnFromFunction(null);
		return null;
	}

	/*
	 * Returns the other Stargate's direction relative to its position
	 */
	public Direction getExitDirection() {
		SkeletonLogger.functionCalled(this, "getExitDirection", null);

		ProjectileType otherType = ProjectileType.getOppositeType(type);
		Stargate exitStargate = stargates.get(otherType);

		if (exitStargate != null) {
			SkeletonLogger.returnFromFunction(exitStargate.direction);
			return exitStargate.direction;
		}

		SkeletonLogger.returnFromFunction(null);
		return null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "_" + type.toString();
	}
}
