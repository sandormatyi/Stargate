package controller.events;

import gamemodel.MapElement;
import gamemodel.ZPM;

public interface IZPMEventListener {
	/*
	 * Called when a ZPM has been created
	 */
	void onZPMCreated(ZPM zpm, MapElement position);

	/*
	 * Called when a ZPM has been picked up
	 */
	void onZPMPickedUp(ZPM zpm);
}