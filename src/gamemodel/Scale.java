package gamemodel;

import debug.ProtoLogger;

public class Scale extends MapElement {

	/*
	 * Constructor inherited from base class
	 */
	public Scale(Coord coord) {
		super(coord);
	}

	private int weightCount = 0;
	static final private int weightLimit = 2;
	private Door door = null;

	/*
	 * Door setter
	 */
	public void setDoor(Door door) {
		this.door = door;
	}

	/*
	 * Increment weightCount and open the door if it is above the limit
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		ProtoLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		player.setPosition(this);

		int playerWeight = player.getWeight();
		weightCount += playerWeight;

		ProtoLogger.log("A(z) " + this.toString() + " mezőre " + playerWeight + " egységnyi súly került, összesen "
				+ weightCount + " súly van rajta");

		if (door != null && weightCount >= weightLimit) {
			ProtoLogger.log(
					"Mivel legalább " + weightLimit + " súly van a mérlegen, a(z) " + door.toString() + " kinyílt");

			door.setOpened(true);
		}
	}

	/*
	 * Decrement weightCount and close the door if it is below the limit
	 */
	@Override
	public void handlePlayerLeave(Player player) {
		int playerWeight = player.getWeight();
		weightCount -= playerWeight;
		if (weightCount < 0)
			ProtoLogger.logError("Weight count of scale cannot be negative");

		ProtoLogger.log("A(z) " + this.toString() + " mezőről " + playerWeight + " egységnyi súly lekerült, összesen "
				+ weightCount + " súly van rajta");

		if (door != null && weightCount < weightLimit) {
			ProtoLogger.log(
					"Mivel nincsen legalább " + weightLimit + " súly a mérlegen, a(z) " + door.toString() + " bezárul");
			door.setOpened(false);
		}
	}

	/*
	 * Increment weightCount and open the door if it is above the limit
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		ProtoLogger.log("Sikeres dobozletétel a(z) " + this.toString() + " mezőre");

		box.setPosition(this);

		boxes.push(box);

		incrementWeight();
	}

	/*
	 * Decrement weightCount and close the door if it is below the limit
	 */
	@Override
	public void handleBoxPickUp(Box box) {
		ProtoLogger.log("Sikeres dobozfelvétel a(z) " + this.toString() + " mezőről");

		box.setPosition(null);

		if (!boxes.remove(box)) {
			ProtoLogger.logError("Trying to remove a box from a field that does not contain the box");
		}

		decrementWeight();
	}

	/*
	 * The projectile harmlessly goes through
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);

		if (replicator != null)
			replicator.destroy();
	}

	/*
	 * A replicator arrives
	 */
	@Override
	public void handleReplicatorArrive(Direction dir, Replicator replicator) {
		ProtoLogger.log("Sikeresen átlépett a következő mezőre: " + this.toString());

		replicator.setPosition(this);
		this.replicator = replicator;
	}

	/*
	 * A replicator leaves
	 */
	@Override
	public void handleReplicatorLeave(Replicator replicator) {
		this.replicator = null;
	};

	/*
	 * Increments the weight on the scale
	 */
	@Override
	public void incrementWeight() {
		int previousWeightCount = weightCount;
		weightCount += 1;

		ProtoLogger.log("A(z) " + this.toString() + " mezőre 1 egységnyi súly került, összesen " + weightCount
				+ " súly van rajta");

		if (weightCount >= weightLimit && previousWeightCount < weightLimit && door != null) {
			ProtoLogger.log(
					"Mivel legalább " + weightLimit + " súly van a mérlegen, a(z) " + door.toString() + " kinyílt");

			door.setOpened(true);
		}
	}

	/*
	 * Decrements the weight on the scale
	 */
	@Override
	public void decrementWeight() {
		int previousWeightCount = weightCount;
		weightCount -= 1;

		if (weightCount < 0)
			ProtoLogger.logError("Weight count of scale cannot be negative");

		ProtoLogger.log("A(z) " + this.toString() + " mezőről 1 egységnyi súly lekerült, összesen " + weightCount
				+ " súly van rajta");

		if (weightCount < weightLimit && previousWeightCount >= weightLimit && door != null) {
			ProtoLogger.log(
					"Mivel nincsen legalább " + weightLimit + " súly a mérlegen, a(z) " + door.toString() + " bezárul");

			door.setOpened(false);
		}
	}
}
