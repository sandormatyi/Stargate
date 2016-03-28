package gamemodel;

import java.util.HashMap;

import debug.SkeletonLogger;

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
		SkeletonLogger.functionCalled(this, "handlePlayerArrive", new Object[] { dir, player });

		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				player.setPosition(exitPosition);
				player.turn(exitDirection);
				player.move();

				SkeletonLogger.returnFromFunction(null);
				return;
			}
		}

		// If the player did not step into an open wormhole, move him back to
		// the previous MapElement
		super.handlePlayerArrive(oppositeDirection, player);
		SkeletonLogger.returnFromFunction(null);
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
		SkeletonLogger.functionCalled(this, "handleProjectileArrive", new Object[] { dir, projectile });

		projectile.setPosition(this);

		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate == null) {
			stargate = projectile.openStargate();
			stargates.put(oppositeDirection, stargate);
		}

		projectile.destroy();

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * If the box is put into an open wormhole, transport it to the exit of the
	 * wormhole
	 */
	@Override
	public void handleBoxPutDown(Direction dir, Box box) {
		SkeletonLogger.functionCalled(this, "handleBoxPutDown", new Object[] { dir, box });

		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				MapElement destination = exitPosition.getNeighbour(exitDirection);

				if (destination != null) {
					destination.handleBoxPutDown(exitDirection, box);

					SkeletonLogger.returnFromFunction(null);
					return;
				}
			}
		}

		// If there is no open wormhole through the given direction, move it
		// back to the previous MapElement
		super.handleBoxPutDown(oppositeDirection, box);
		SkeletonLogger.returnFromFunction(null);
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
		SkeletonLogger.functionCalled(this, "getBox", new Object[] { dir });

		Direction oppositeDirection = Direction.getOppositeDirection(dir);
		Stargate stargate = stargates.get(oppositeDirection);

		if (stargate != null) {
			MapElement exitPosition = stargate.getExitPosition();
			Direction exitDirection = stargate.getExitDirection();

			if (exitPosition != null && exitDirection != null) {
				MapElement destination = exitPosition.getNeighbour(exitDirection);

				if (destination != null) {
					Box box = destination.getBox(exitDirection);

					SkeletonLogger.returnFromFunction(box);
					return box;
				}
			}
		}

		SkeletonLogger.returnFromFunction(null);
		return null;
	}
}
