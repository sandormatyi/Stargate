package gamemodel;

public class Player extends Movable {

	private Direction direction;
	private boolean isAlive = true;
	private ProjectileType projType = ProjectileType.YELLOW;
	private Box box = null;

	public Player(MapElement position, Direction direction) {
		super(position);
		this.direction = direction;
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

	public boolean isAlive() {
		return isAlive;
	}

	public void turn(Direction direction) {
		// TODO
	}

	public void shoot() {
		// TODO
	}

	public void pickUpBox() {
		// TODO
	}

	public void putDownBox() {
		// TODO
	}

	public void die() {
		// TODO
	}
}
