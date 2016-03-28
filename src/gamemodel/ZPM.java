package gamemodel;

import gamemodel.events.ModelEventSource;

public class ZPM {

	/*
	 * Notifies the listeners that it has been picked up
	 */
	public void handlePickUp() {
		ModelEventSource.notifyZPMPickedUp(this);
	}
}
