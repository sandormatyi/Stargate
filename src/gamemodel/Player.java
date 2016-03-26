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
		element.handlePlayerArrive(dir, this);
	}

	@Override
	public void leaveMapElement(MapElement element) {
		// TODO
		position.handlePlayerLeave();
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
		this.direction = direction;
	}

	public void shoot() {
		// Ezt nem tudom hogy kéne egyelõre
	}

	public void pickUpBox() {
		// TODO
		MapElement nextposition;
		nextposition = position.getNeighbour(direction);
		nextposition.getBox();
		box.leaveMapElement(nextposition);
	}

	public void putDownBox() {
		// TODO
		MapElement nextposition;
		nextposition = position.getNeighbour(direction);
		nextposition.getBox();
		box.arriveOnMapElement(direction, nextposition);
	}

	public void die() {
		// TODO
		this.isAlive = false;
	}
}
