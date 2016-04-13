package gamemodel;

public class ONeill extends Player {

	/*
	 * Constructor inherited from base class
	 */
	public ONeill(MapElement position, Direction direction) {
		super(position, direction);

		projTypes = new ProjectileType[] { ProjectileType.YELLOW, ProjectileType.BLUE };
	}
}
