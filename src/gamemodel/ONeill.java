package gamemodel;

public class ONeill extends Player {

	/*
	 * Constructor inherited from base class
	 */
	public ONeill(MapElement position) {
		super(position);

		projTypes = new ProjectileType[] { ProjectileType.YELLOW, ProjectileType.BLUE };
	}
}
