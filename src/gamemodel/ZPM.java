package gamemodel;

import debug.SkeletonLogger;
import gamemodel.events.ModelEventSource;

public class ZPM {

	/*
	 * Notifies the listeners that it has been picked up
	 */
	public void handlePickUp() {
		SkeletonLogger.functionCalled(this, "handlePickUp", null);

		ModelEventSource.notifyZPMPickedUp(this);

		SkeletonLogger.returnFromFunction(null);
	}

	/*
	 * Get the Class name as String
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
