package gamemodel.events;

import gamemodel.ZPM;

public interface IZPMPickedUpListener {

	/*
	 * Handles the picking up of a ZPM
	 */
	void onZPMPickedUp(ZPM zpm);
}
