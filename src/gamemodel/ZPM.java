package gamemodel;

import gamemodel.events.ModelEventSource;

public class ZPM {

	/*
	 * Notifies the listeners that it has been picked up
	 */
	public void handlePickUp() {
		ModelEventSource.notifyZPMPickedUp(this);
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
