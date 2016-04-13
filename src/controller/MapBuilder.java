package controller;

import java.util.HashSet;

import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.Gap;
import gamemodel.ONeill;
import gamemodel.Player;
import gamemodel.Road;
import gamemodel.Scale;
import gamemodel.SpecialWall;
import gamemodel.Wall;
import gamemodel.ZPM;

class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player player;

	/*
	 * Build the map and store a reference to the instantiated player and store
	 * the instantiated ZPMs in the set
	 */
	public void buildSkeletonMap() {
		// Initialize ZPM
		ZPM zpm = new ZPM();
		zpmSet.add(zpm);

		// Initialize MapElements
		SpecialWall A1 = new SpecialWall("A1");
		Gap A2 = new Gap("A2");
		Road A3 = new Road("A3");
		Door B3 = new Door("B3");
		Scale B1 = new Scale("B1");
		Road B2 = new Road("B2");
		SpecialWall C1 = new SpecialWall("C1");
		Road C2 = new Road("C2");
		Wall C3 = new Wall("C3");
		SpecialWall D2 = new SpecialWall("D2");

		// Initialize player, set box, zpm, door
		player = new ONeill(B2, Direction.WEST);
		B1.setDoor(B3);
		A3.setZpm(zpm);
		Box box = new Box(B2);
		box.arriveOnMapElement(null, B2);

		// Handle neighborhoods of northWest
		A1.setNeighbour(Direction.EAST, A2);
		A1.setNeighbour(Direction.SOUTH, B1);

		// Handle neighborhoods of north
		A2.setNeighbour(Direction.WEST, A1);
		A2.setNeighbour(Direction.SOUTH, B2);
		A2.setNeighbour(Direction.EAST, A3);

		// Handle neighborhoods of northEast
		A3.setNeighbour(Direction.WEST, A2);
		A3.setNeighbour(Direction.SOUTH, B3);

		// Handle neighborhoods of west
		B1.setNeighbour(Direction.NORTH, A1);
		B1.setNeighbour(Direction.EAST, B2);
		B1.setNeighbour(Direction.SOUTH, C1);

		// Handle neighborhoods of middle
		B2.setNeighbour(Direction.NORTH, A2);
		B2.setNeighbour(Direction.WEST, B1);
		B2.setNeighbour(Direction.SOUTH, C2);
		B2.setNeighbour(Direction.EAST, B3);

		// Handle neighborhoods of east
		B3.setNeighbour(Direction.NORTH, A3);
		B3.setNeighbour(Direction.WEST, B2);
		B3.setNeighbour(Direction.SOUTH, C3);

		// Handle neighborhoods of southWest
		C1.setNeighbour(Direction.NORTH, B1);
		C1.setNeighbour(Direction.EAST, C2);

		// Handle neighborhoods of south
		C2.setNeighbour(Direction.WEST, C1);
		C2.setNeighbour(Direction.NORTH, B2);
		C2.setNeighbour(Direction.EAST, C3);
		C2.setNeighbour(Direction.SOUTH, D2);

		// Handle neighborhoods of southEast
		C3.setNeighbour(Direction.NORTH, B3);
		C3.setNeighbour(Direction.WEST, C2);

		// Handle neighborhoods of southSouth
		D2.setNeighbour(Direction.NORTH, C2);
	}

	public Controller createController(Game game) {
		if (game == null || player == null || zpmSet.isEmpty())
			return null;

		return new Controller(game, player, zpmSet);
	}
}
