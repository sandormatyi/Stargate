package gamemodel;

import java.util.HashMap;

public class SpecialWall extends MapElement {

	/*
	 * A HashMap that stores the open stargates by their relative position to
	 * the field
	 */
	private HashMap<Direction, Stargate> stargates = new HashMap<Direction, Stargate>(4);

	/*
	 * If the player steps into an open wormhole, transport him to the exit of
	 * the wormhole
	 */
	@Override
	public void handlePlayerArrive(Direction dir, Player player) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				player.setPosition(exitPosition);
				player.turn(exitDirection);
				player.move();

				return;
			}
		}

		// If the player did not step into an open wormhole, move him back to
		// the previous MapElement
		super.handlePlayerArrive(oppositeDirection, player);
	}

	@Override
	public void handlePlayerLeave() {
		// TODO
	}

	/*
	 * If there is already an open stargate on the edge with which the
	 * projectile has collided, destroy the projectile, else, open a new
	 * stargate
	 */
	@Override
	public void handleProjectileArrive(Direction dir, Projectile projectile) {
		projectile.setPosition(this);

		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate == null) {
			stargate = projectile.openStargate();
			stargates.put(oppositeDirection, stargate);
		} else {
			projectile.destroy();
		}
	}

	/*
	 * If the box is put into an open wormhole, transport it to the exit of the
	 * wormhole
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				MapElement destination = exitPosition.getNeighbour(exitDirection);

				if (destination != null) {
					destination.handleBoxPutDown(exitDirection, box);
				}
			}
		}

		// If there is no open wormhole through the given direction, move it
		// back to the previous MapElement
		super.handleBoxPutDown(oppositeDirection, box);
	}

	@Override
	public void handleBoxPickUp() {
		// TODO
	}

	/*
	 * If there is an open wormhole through the given direction, returns the box
	 * on the MapElement before the exit of the wormhole
	 */
	@Override
	public Box getBox(Direction dir) {
		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				MapElement destination = exitPosition.getNeighbour(exitDirection);

				if (destination != null)
					return destination.getBox(exitDirection);
			}
		}

		return null;
	}
}
