package controller.events;

import gamemodel.MapElement;

public interface IMapEventListener {

	/*
	 * Called when a new MapElement is created
	 */
	void onMapElementCreated(MapElement mapElement);
}
