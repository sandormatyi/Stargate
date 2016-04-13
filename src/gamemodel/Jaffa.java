package gamemodel;

public class Jaffa extends Player {

	/*
	 * Constructor inherited from base class
	 */
	public Jaffa(MapElement position, Direction direction) {
		super(position, direction);

		projTypes = new ProjectileType[] { ProjectileType.RED, ProjectileType.GREEN };
	}
}
