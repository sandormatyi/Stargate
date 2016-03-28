package controller;

import java.util.HashSet;

import gamemodel.Player;
import gamemodel.ZPM;

public class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player player;

	public void buildMap() {
		// TODO: Build the map and store a reference to the player and store the
		// ZPMs in the set
	}

	public Controller createController(Game game) {
		if (game == null || player == null || zpmSet.isEmpty())
			return null;

		return new Controller(game, player, zpmSet);
	}
}
