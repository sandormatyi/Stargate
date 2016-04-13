package gamemodel;

public class ONeill extends Player {

	public ONeill(MapElement position, Direction direction) {
		super(position, direction);

		projTypes = new ProjectileType[] { ProjectileType.YELLOW, ProjectileType.BLUE };
	}
}
