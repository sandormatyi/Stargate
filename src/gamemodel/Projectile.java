package gamemodel;

import debug.SkeletonLogger;
import gamemodel.events.ModelEventSource;

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
		SkeletonLogger.functionCalled(this, "arriveOnMapElement", new Object[] { dir, element });

		element.handleProjectileArrive(dir, this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * The Projectile leaves a mapElement
	 */
	@Override
	public void leaveMapElement(MapElement element) {
		// TODO: Ezt kiírjuk-e?
	}

	/*
	 * Move the projectile to the next MapElement
	 */
	@Override
	public void move() {
		SkeletonLogger.functionCalled(this, "move", null);

		this.leaveMapElement(position);

		MapElement nextposition = position.getNeighbour(direction);

		if (nextposition != null) {
			arriveOnMapElement(direction, nextposition);
		} else {
			System.err.println("Projectile tried to move on a MapElement that does not exist!");
			destroy();
		}

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Open a stargate
	 */
	public Stargate openStargate() {
		SkeletonLogger.functionCalled(this, "openStargate", null);

		Stargate stargate = Stargate.createStargate(position, type, Direction.getOppositeDirection(direction));

		SkeletonLogger.returnFromFunction(stargate);
		return stargate;
	}

	/*
	 * Destroy the projectile
	 */
	public void destroy() {
		SkeletonLogger.functionCalled(this, "destroy", null);

		ModelEventSource.notifyProjectileDestroyed(this);

		SkeletonLogger.returnFromFunction(null);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "_" + type.toString();
	}
}
