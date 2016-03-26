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
		element.handlePlayerArrive(dir, this);
	}

	@Override
	public void leaveMapElement(MapElement element) {
		element.handlePlayerLeave();
	}

	//Player moves
	@Override
	public void move() {
		this.leaveMapElement(position);
		//Get the Next MapElement
		MapElement nextposition = position.getNeighbour(direction);
		//If there is not nextPosition, then return.
		if (nextposition == null) return;
		//Call the nextMapElement's arrive method
		this.arriveOnMapElement(direction,nextposition);
	}
	
	//Player is alive?
	public boolean isAlive() {
		return isAlive;
	}
	
	//Turn the player to a direction
	public void turn(Direction direction) {
		this.direction = direction;
	}
	//Shoot a projectile
	public void shoot() {
		//Create Projectile
		Projectile projectile = new Projectile(position, direction, projType);
		//Set the projectile type the the other one.
		if (projType == ProjectileType.BLUE) projType = ProjectileType.YELLOW;
		else projType = ProjectileType.BLUE;
		// Ezt nem tudom hogy kéne egyelõre
	}
	//Pick up a box from the next mapElement
	public void pickUpBox() {
		//If the player already has a box, then return
		if (this.box != null) return;
		//Otherwise get the box from the next element
		MapElement nextposition = position.getNeighbour(direction);
		//If there is not nextPosition, then return.
		if (nextposition == null) return;
		this.box = nextposition.getBox();
		//If we get a box, then set it's position to null.
		if (this.box != null) box.leaveMapElement(nextposition);
	}
	
	//Put down a box to the next mapElement
	public void putDownBox() {
		MapElement nextposition = position.getNeighbour(direction);
		//If there is not nextPosition, then return.
		if (nextposition == null) return;
		Box nextPositionBox = nextposition.getBox();
		//If the nextMapElement already has a box, then return
		if (nextPositionBox != null) return;
		//Otherwise put down the player's box
		box.arriveOnMapElement(direction, nextposition);
		//Set our box to null
		this.box = null;
	}
	
	//Set the isAlive to false
	public void die() {
		// TODO
		this.isAlive = false;
	}
}
