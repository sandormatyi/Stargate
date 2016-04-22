package controller;

import java.net.URL;
import java.util.HashSet;

import debug.ProtoLogger;
import gamemodel.Player;
import gamemodel.ZPM;

class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player oneill;
	private Player jaffa;

	/*
	 * Reads the given map file from the /res/maps folder and if it is a valid
	 * map file, builds the map
	 */
	public void buildMapFromFile(String mapFileName) throws Exception {
		URL resURL = getClass().getClassLoader().getResource(mapFileName);

		if (resURL == null)
			throw new Exception();
		else
			ProtoLogger.logError(resURL.getPath());
	}

	public Controller createController(Game game) {
		if (game == null || oneill == null || jaffa == null || zpmSet.isEmpty())
			return null;

		return new Controller(game, zpmSet, oneill, jaffa, null);
	}
}
