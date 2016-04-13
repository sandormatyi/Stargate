package gamemodel;

import debug.ProtoLogger;

public class Scale extends MapElement {

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
	 * The projectile harmlessly goes through
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);
	}

	/*
	 * Decrement weightCount and close the door if it is below the limit
	 */
	@Override
	public void handleBoxPickUp(Box box) {
		this.box.setPosition(null);
		weightCount--;

		if (weightCount < 0)
			ProtoLogger.logError("Weight count of scale cannot be negative");

		ProtoLogger.log("A(z) " + this.toString() + " mezőről 1 egységnyi súly lekerült, összesen " + weightCount
				+ " súly van rajta");

		if (door != null && weightCount < weightLimit) {
			ProtoLogger.log(
					"Mivel nincsen legalább " + weightLimit + " súly a mérlegen, a(z) " + door.toString() + " bezárul");

			door.setOpened(false);
		}

		this.box = null;
	}

	/*
	 * Increment weightCount and open the door if it is above the limit
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		box.setPosition(this);
		weightCount++;

		ProtoLogger.log("A(z) " + this.toString() + " mezőre 1 egységnyi súly került, összesen " + weightCount
				+ " súly van rajta");

		if (door != null && weightCount >= weightLimit) {
			ProtoLogger.log(
					"Mivel legalább " + weightLimit + " súly van a mérlegen, a(z) " + door.toString() + " kinyílt");

			door.setOpened(true);
		}

		if (this.box != null)
			this.box.respawn();

		this.box = box;
	}
}
