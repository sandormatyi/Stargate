package gamemodel;

public class Stargate {

	// FIXME: Use HashMap<Direction, Stargate> instead?
	private static Stargate blueStargate = null;
	private static Stargate yellowStargate = null;

	private SpecialWall position;
	private ProjectileType type;
	private Direction direction;

	public Stargate(SpecialWall position, ProjectileType type, Direction direction) {
		this.position = position;
		this.type = type;
		this.direction = direction;
	}

	public MapElement getExitPosition() {
		// TODO
		return null;
	}
}
