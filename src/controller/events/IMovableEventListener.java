package controller.events;

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
}
