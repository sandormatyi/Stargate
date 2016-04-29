package controller;

import java.util.Vector;

import controller.events.ControllerEventSource;
import debug.GameLogger;
import debug.RandomGenerator;
import gamemodel.Direction;
import gamemodel.Gap;
import gamemodel.MapElement;
import gamemodel.Road;

public class MapHelper {
	/*
	 * The set of roads on the map
	 * 
	 * (Can be replaced with HashSet in the final version)
	 */
	private static Vector<Road> roads = null;

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
		Road road = new Road(gap.getCoord());

		// Update the neighbours
		for (Direction dir : Direction.values()) {
			MapElement neighbour = gap.getNeighbour(dir);

			if (neighbour != null) {
				road.setNeighbour(dir, neighbour);
				neighbour.setNeighbour(Direction.getOppositeDirection(dir), road);
			}
		}

		// Send notification that the Gap has been replaced
		ControllerEventSource.notifyMapElementRemoved(gap);
		ControllerEventSource.notifyMapElementCreated(road);
	}

	/*
	 * Returns a random road
	 */
	public static Road getRandomRoad() {
		Road road = null;

		if (roads == null) {
			GameLogger.logError("A MapHelper nem lett inicializálva!");
		} else {
			road = roads.elementAt(RandomGenerator.getRandomNumber(roads.size()));
		}

		return road;
	}
}
