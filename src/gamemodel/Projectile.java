package gamemodel;

public class Projectile extends Movable {

	private Direction direction;
	private ProjectileType type;

	public Projectile(MapElement position, Direction direction, ProjectileType type) {
		super(position);
		this.direction = direction;
		this.type = type;
	}

	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		// TODO
	}

	@Override
	public void leaveMapElement(MapElement element) {
		// TODO
	}

	@Override
	public void move() {
		// TODO
	}

	public Stargate openStargate() {
		// TODO
		return null;
	}
}
