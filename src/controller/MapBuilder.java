package controller;

import java.util.HashSet;

import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.Gap;
import gamemodel.Player;
import gamemodel.Road;
import gamemodel.Scale;
import gamemodel.SpecialWall;
import gamemodel.Wall;
import gamemodel.ZPM;

public class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player player;

	/*
	 * Build the map and store a reference to the instantiated player and store
	 * the instantiated ZPMs in the set
	 */
	public void buildMap() {
		// Initialize zpm
		ZPM zpm = new ZPM();
		zpmSet.add(zpm);

		// Initialize MapElements
		SpecialWall northWest = new SpecialWall();
		Gap north = new Gap();
		Road northEast = new Road();
		Door east = new Door();
		Scale west = new Scale();
		Road middle = new Road();
		SpecialWall southWest = new SpecialWall();
		Road south = new Road();
		Wall southEast = new Wall();

		// Initialize player, set box, zpm, door
		player = new Player(middle, Direction.WEST);
		west.setDoor(east);
		south.setZpm(zpm);
		Box box = new Box(middle, middle);
		middle.setBox(box);

		// Handle neighborhoods of northWest
		northWest.setNeighbour(Direction.EAST, north);
		northWest.setNeighbour(Direction.SOUTH, west);

		// Handle neighborhoods of north
		north.setNeighbour(Direction.WEST, northWest);
		north.setNeighbour(Direction.SOUTH, middle);
		north.setNeighbour(Direction.EAST, northEast);

		// Handle neighborhoods of northEast
		northEast.setNeighbour(Direction.WEST, north);
		northEast.setNeighbour(Direction.SOUTH, east);

		// Handle neighborhoods of west
		west.setNeighbour(Direction.NORTH, northWest);
		west.setNeighbour(Direction.EAST, middle);
		west.setNeighbour(Direction.SOUTH, southWest);

		// Handle neighborhoods of middle
		middle.setNeighbour(Direction.NORTH, north);
		middle.setNeighbour(Direction.WEST, west);
		middle.setNeighbour(Direction.SOUTH, south);
		middle.setNeighbour(Direction.EAST, east);

		// Handle neighborhoods of east
		east.setNeighbour(Direction.NORTH, northEast);
		east.setNeighbour(Direction.WEST, middle);
		east.setNeighbour(Direction.SOUTH, southEast);

		// Handle neighborhoods of southWest
		southWest.setNeighbour(Direction.NORTH, west);
		southWest.setNeighbour(Direction.EAST, south);

		// Handle neighborhoods of south
		south.setNeighbour(Direction.WEST, southWest);
		south.setNeighbour(Direction.NORTH, middle);
		south.setNeighbour(Direction.EAST, southEast);

		// Handle neighborhoods of southEast
		southEast.setNeighbour(Direction.NORTH, east);
		southEast.setNeighbour(Direction.WEST, south);
	}

	public Controller createController(Game game) {
		if (game == null || player == null || zpmSet.isEmpty())
			return null;

		return new Controller(game, player, zpmSet);
	}
}
