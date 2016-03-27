package gamemodel;

public class Projectile extends Movable {

	private Direction direction;
	private ProjectileType type;

	public Projectile(MapElement position, Direction direction, ProjectileType type) {
		super(position);
		this.direction = direction;
		this.type = type;
	}
	
	/*
	 * The Projectile arrives on a mapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handleProjectileArrive(dir, this);
	}

	/*
	 * The Projectile leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
	}

	/*
	 * Move the projectile to the next MapElement
	 */
	@Override
	public void move() {
		this.leaveMapElement(position);
		MapElement nextPosition = position.getNeighbour(direction);
		if (nextPosition == null)
			return;
		this.arriveOnMapElement(direction, nextPosition);
	}
	
	/*
	 * Open a stargate
	 */
	public Stargate openStargate() {
		//A positiont nem lehet neki átadni, mert az nem specialwall.
		Stargate Stargate = new Stargate(position, type, direction);
		return Stargate;
	}
}
