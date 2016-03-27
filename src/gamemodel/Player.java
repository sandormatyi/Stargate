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

	/*
	 * Player arrives on a MapElement
	 */
	@Override
	public void arriveOnMapElement(Direction dir, MapElement element) {
		element.handlePlayerArrive(dir, this);
	}

	/*
	 * Player leaves a MapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		element.handlePlayerLeave();
	}

	/*
	 * Player moves
	 */
	@Override
	public void move() {
		this.leaveMapElement(position);
		MapElement nextposition = position.getNeighbour(direction);
		if (nextposition == null)
			return;
		this.arriveOnMapElement(direction, nextposition);
	}

	/*
	 * Player is alive?
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/*
	 * Get the direction of the player
	 */
	public Direction getDirection() {
		return direction;
	}

	/*
	 * Turn the player to a direction
	 */
	public void turn(Direction direction) {
		this.direction = direction;
	}

	/*
	 * Shoot a projectile
	 */
	public void shoot() {
		// Ezt nem haszn�lod nem kell
		// Projectile projectile = new Projectile(position, direction,
		// projType);
		if (projType == ProjectileType.BLUE) {
			projType = ProjectileType.YELLOW;
		} else {
			projType = ProjectileType.BLUE;
		}
	}

	/*
	 * Pick up a box from the next mapElement
	 */
	public void pickUpBox() {
		if (this.box != null)
			return;

		MapElement nextPosition = position.getNeighbour(direction);
		if (nextPosition == null)
			return;

		this.box = nextPosition.getBox(direction);
		if (this.box != null)
			box.leaveMapElement(nextPosition);
	}

	/*
	 * Put down a box to the next mapElement
	 */
	public void putDownBox() {
		MapElement nextPosition = position.getNeighbour(direction);
		if (nextPosition == null)
			return;

		this.box.arriveOnMapElement(direction, nextPosition);
		this.box = null;
	}

	/*
	 * Set the isAlive to false
	 */
	public void die() {
		this.isAlive = false;
	}
}
