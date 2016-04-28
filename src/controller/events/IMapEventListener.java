package controller.events;

import gamemodel.MapElement;

public interface IMapEventListener {

	/*
	 * Called when a new MapElement is created
	 */
	void onMapElementCreated(MapElement mapElement);

	/*
	 * Called when a MapElement is removed
	 */
	void onMapElementRemoved(MapElement mapElement);
}
