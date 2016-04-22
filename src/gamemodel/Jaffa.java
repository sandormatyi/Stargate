package gamemodel;

public class Jaffa extends Player {

	/*
	 * Constructor inherited from base class
	 */
	public Jaffa(MapElement position) {
		super(position);

		projTypes = new ProjectileType[] { ProjectileType.RED, ProjectileType.GREEN };
	}
}
