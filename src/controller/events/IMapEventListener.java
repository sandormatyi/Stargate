package controller.events;

import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.ProjectileType;

public interface IMapEventListener {

	/*
	 * Called when a new MapElement is created
	 */
	void onMapElementCreated(MapElement mapElement);

	/*
	 * Called when a MapElement is removed
	 */
	void onMapElementRemoved(MapElement mapElement);

	/*
	 * Called when a stargate is opened
	 */
	void onStargateOpened(MapElement mapElement, ProjectileType type, Direction direction);

	/*
	 * Called when the state of a door is changed
	 */
	void onDoorStateChanged(Door door);
}
