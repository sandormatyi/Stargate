package controller;

import java.util.Vector;

import gamemodel.Gap;
import gamemodel.Road;

public class MapHelper {
	/*
	 * The set of roads on the map
	 * 
	 * (Can be replaced with HashSet in the final version)
	 */
	private static Vector<Road> roads;

	/*
	 * Initializes the MapHelper
	 */
	public static void setRoads(Vector<Road> roads) {
		MapHelper.roads = roads;
	}

	/*
	 * Replaces a gap given as parameter with a new road
	 */
	public static void replaceWithRoad(Gap gap) {
		// TODO
	}

	/*
	 * Returns a random road
	 */
	public static Road getRandomRoad() {
		// TODO
		return null;
	}
}
