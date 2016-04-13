package gamemodel;

public class Jaffa extends Player {

	public Jaffa(MapElement position, Direction direction) {
		super(position, direction);

		projTypes = new ProjectileType[] { ProjectileType.RED, ProjectileType.GREEN };
	}

}
