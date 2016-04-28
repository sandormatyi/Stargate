package controller.events;

import gamemodel.Box;
import gamemodel.MapElement;
import gamemodel.Movable;

public interface IMovableEventListener {

	/*
	 * Called when the state of a Movable is changed
	 */
	void onMovableChanged(Movable movable);

	/*
	 * Called when a Movable is destroyed
	 */
	void onMovableDestroyed(Movable movable);

	/*
	 * Called when a box is put down
	 */
	void onBoxPutDown(Box box, MapElement mapElement);

	/*
	 * Called when a box is picked up
	 */
	void onBoxPickedUp(Box box, MapElement mapElement);
}
