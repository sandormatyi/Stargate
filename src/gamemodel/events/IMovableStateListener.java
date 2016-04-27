package gamemodel.events;

import gamemodel.Movable;

public interface IMovableStateListener {

	/*
	 * Called when the state of a movable is changed
	 */
	void onMovableChanged(Movable movable);
}
