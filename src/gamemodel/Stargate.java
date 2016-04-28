package gamemodel;

import java.util.HashMap;

import gamemodel.events.ModelEventSource;

public class Stargate {

	/*
	 * A HashMap to store the stargates indexed by their types
	 */
	private static HashMap<ProjectileType, Stargate> stargates = new HashMap<ProjectileType, Stargate>(2);

	private MapElement position;
	private ProjectileType type;
	private Direction direction;

	private Stargate(MapElement position, ProjectileType type, Direction direction) {
		this.position = position;
		this.type = type;
		this.direction = direction;
	}

	/*
	 * Static method to create a Stargate
	 */
	public static Stargate createStargate(MapElement position, ProjectileType type, Direction direction) {
		Stargate stargate = new Stargate(position, type, direction);

		if (stargates.containsKey(type)) {
			Stargate stargateToBeRemoved = stargates.get(type);

			((SpecialWall) stargateToBeRemoved.position).removeStargate(stargateToBeRemoved.direction);
		}

		stargates.put(type, stargate);

		// Send notification that a Stargate has been opened
		ModelEventSource.notifyStargateOpened(position, type, direction);

		return stargate;
	}

	/*
	 * Returns the position of the other Stargate
	 */
	public MapElement getExitPosition() {
		ProjectileType otherType = ProjectileType.getOppositeType(type);
		Stargate exitStargate = stargates.get(otherType);

		if (exitStargate != null) {
			return exitStargate.position;
		}

		return null;
	}

	/*
	 * Returns the other Stargate's direction relative to its position
	 */
	public Direction getExitDirection() {
		ProjectileType otherType = ProjectileType.getOppositeType(type);
		Stargate exitStargate = stargates.get(otherType);

		if (exitStargate != null) {
			return exitStargate.direction;
		}

		return null;
	}

	@Override
	public String toString() {
		return type.toString();
	}
}
