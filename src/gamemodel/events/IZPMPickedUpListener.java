package gamemodel.events;

import gamemodel.Player;
import gamemodel.ZPM;

public interface IZPMPickedUpListener {

	/*
	 * Handles the picking up of a ZPM
	 */
	void onZPMPickedUp(Player player, ZPM zpm);
}
