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

class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player player;

	void printProtoStartMap() {
		System.out.println("  |  A  |  B  |  C  |  D  |  E  |  F  |  ");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("1 | WALL| WALL| WALL| WALL| WALL| WALL| 1");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("2 | WALL| ROAD| ROAD| GAP | DOOR|SPECW| 2");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("3 | WALL| ROAD| ROAD| WALL| ROAD| WALL| 3");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("4 |SPECW|SCALE| GAP | GAP | ROAD| WALL| 4");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("5 | WALL|SPECW| ROAD| WALL| ROAD|SPECW| 5");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("6 | WALL| WALL|SPECW| WALL| WALL| WALL| 6");
		System.out.println("--+-----+-----+-----+-----+-----+-----+--");
		System.out.println("  |  A  |  B  |  C  |  D  |  E  |  F  |  ");
	}

	/*
	 * Ha ideiglenes felvennénk a a moveablebe egy koordináta stringet, ami
	 * tartalmazza a koordinátáját (A1, etc..) akkor kiirathatnánk a térkép
	 * alatt minden objektum helyét és így meg lenne oldva minden infó
	 * 
	 * Mert ha csak a körülött lévõket látná és a a térképet akkor valószínûleg
	 * megint bénáznak majd és 50 percig keresik majd, hogy óóó ez melyik része
	 * is a nagy térképnek
	 */

	public void buildProtoMap() {
		// Initialize ZPMs
		ZPM zpm1 = new ZPM();
		ZPM zpm2 = new ZPM();
		ZPM zpm3 = new ZPM();
		ZPM zpm4 = new ZPM();
		zpmSet.add(zpm1);
		zpmSet.add(zpm2);
		zpmSet.add(zpm3);
		zpmSet.add(zpm4);

		// Initialize MapElements
		// First row
		Wall A1 = new Wall();
		Wall B1 = new Wall();
		Wall C1 = new Wall();
		Wall D1 = new Wall();
		Wall E1 = new Wall();
		Wall F1 = new Wall();
		// Second row
		Wall A2 = new Wall();
		Road B2 = new Road();
		Road C2 = new Road();
		Gap D2 = new Gap();
		Door E2 = new Door();
		SpecialWall F2 = new SpecialWall();
		// Third row
		Wall A3 = new Wall();
		Road B3 = new Road();
		Road C3 = new Road();
		Wall D3 = new Wall();
		Road E3 = new Road();
		Wall F3 = new Wall();
		// Fourth row
		SpecialWall A4 = new SpecialWall();
		Scale B4 = new Scale();
		Gap C4 = new Gap();
		Gap D4 = new Gap();
		Road E4 = new Road();
		Wall F4 = new Wall();
		// Fifth row
		Wall A5 = new Wall();
		SpecialWall B5 = new SpecialWall();
		Road C5 = new Road();
		Wall D5 = new Wall();
		Road E5 = new Road();
		SpecialWall F5 = new SpecialWall();
		// Sixth row
		Wall A6 = new Wall();
		Wall B6 = new Wall();
		SpecialWall C6 = new SpecialWall();
		Wall D6 = new Wall();
		Wall E6 = new Wall();
		Wall F6 = new Wall();

		// Initialize player, set box, zpm, door
		/*
		 * ONeill = new ONeill(B3, Direction.WEST); Jaffa = new
		 * Jaffa(C1,Direction.WEST);
		 */
		// Initialize Door
		B4.setDoor(E2);
		// Initialize ZPMs
		B2.setZpm(zpm1);
		C3.setZpm(zpm2);
		C5.setZpm(zpm3);
		E4.setZpm(zpm4);
		// Initialize Boxes
		Box box1 = new Box(B3);
		Box box2 = new Box(C1);
		/*
		 * FIRST row
		 */
		// Handle neighborhoods of A1
		A1.setNeighbour(Direction.EAST, B1);
		A1.setNeighbour(Direction.SOUTH, A2);
		// Handle neighborhoods of B1
		B1.setNeighbour(Direction.WEST, A1);
		B1.setNeighbour(Direction.SOUTH, B2);
		B1.setNeighbour(Direction.EAST, C1);
		// Handle neighborhoods of C1
		C1.setNeighbour(Direction.WEST, B1);
		C1.setNeighbour(Direction.SOUTH, C2);
		C1.setNeighbour(Direction.EAST, D1);
		// Handle neighborhoods of D1
		D1.setNeighbour(Direction.WEST, C1);
		D1.setNeighbour(Direction.SOUTH, D2);
		D1.setNeighbour(Direction.EAST, E1);
		// Handle neighborhoods of E1
		E1.setNeighbour(Direction.WEST, D1);
		E1.setNeighbour(Direction.SOUTH, E2);
		E1.setNeighbour(Direction.EAST, F1);
		// Handle neighborhoods of F1
		F1.setNeighbour(Direction.WEST, E1);
		F1.setNeighbour(Direction.SOUTH, F2);

		/*
		 * SECOND row
		 */
		// Handle neighborhoods of A2
		A2.setNeighbour(Direction.NORTH, A1);
		A2.setNeighbour(Direction.EAST, B2);
		A2.setNeighbour(Direction.SOUTH, A3);
		// Handle neighborhoods of B2
		B2.setNeighbour(Direction.NORTH, B1);
		B2.setNeighbour(Direction.WEST, A2);
		B2.setNeighbour(Direction.SOUTH, B3);
		B2.setNeighbour(Direction.EAST, C2);
		// Handle neighborhoods of C2
		C2.setNeighbour(Direction.NORTH, C1);
		C2.setNeighbour(Direction.WEST, B2);
		C2.setNeighbour(Direction.SOUTH, C3);
		C2.setNeighbour(Direction.EAST, D2);
		// Handle neighborhoods of D2
		D2.setNeighbour(Direction.NORTH, D1);
		D2.setNeighbour(Direction.WEST, C2);
		D2.setNeighbour(Direction.SOUTH, D3);
		D2.setNeighbour(Direction.EAST, E2);
		// Handle neighborhoods of E2
		E2.setNeighbour(Direction.NORTH, E1);
		E2.setNeighbour(Direction.WEST, D2);
		E2.setNeighbour(Direction.SOUTH, E3);
		E2.setNeighbour(Direction.EAST, F2);
		// Handle neighborhoods of F2
		F2.setNeighbour(Direction.NORTH, F1);
		F2.setNeighbour(Direction.WEST, E2);
		F2.setNeighbour(Direction.SOUTH, F3);

		/*
		 * THIRD row
		 */
		// Handle neighborhoods of A3
		A3.setNeighbour(Direction.NORTH, A2);
		A3.setNeighbour(Direction.EAST, B3);
		A3.setNeighbour(Direction.SOUTH, A4);
		// Handle neighborhoods of B3
		B3.setNeighbour(Direction.NORTH, B2);
		B3.setNeighbour(Direction.WEST, A3);
		B3.setNeighbour(Direction.SOUTH, B4);
		B3.setNeighbour(Direction.EAST, C3);
		// Handle neighborhoods of C3
		C3.setNeighbour(Direction.NORTH, C2);
		C3.setNeighbour(Direction.WEST, B3);
		C3.setNeighbour(Direction.SOUTH, C4);
		C3.setNeighbour(Direction.EAST, D3);
		// Handle neighborhoods of D3
		D3.setNeighbour(Direction.NORTH, D2);
		D3.setNeighbour(Direction.WEST, C3);
		D3.setNeighbour(Direction.SOUTH, D4);
		D3.setNeighbour(Direction.EAST, E3);
		// Handle neighborhoods of E3
		E3.setNeighbour(Direction.NORTH, E2);
		E3.setNeighbour(Direction.WEST, D3);
		E3.setNeighbour(Direction.SOUTH, E4);
		E3.setNeighbour(Direction.EAST, F3);
		// Handle neighborhoods of F3
		F3.setNeighbour(Direction.NORTH, F2);
		F3.setNeighbour(Direction.WEST, E3);
		F3.setNeighbour(Direction.SOUTH, F4);

		/*
		 * FOURTH row
		 */
		// Handle neighborhoods of A4
		A4.setNeighbour(Direction.NORTH, A3);
		A4.setNeighbour(Direction.EAST, B4);
		A4.setNeighbour(Direction.SOUTH, A5);
		// Handle neighborhoods of B4
		B4.setNeighbour(Direction.NORTH, B3);
		B4.setNeighbour(Direction.WEST, A4);
		B4.setNeighbour(Direction.SOUTH, B5);
		B4.setNeighbour(Direction.EAST, C4);
		// Handle neighborhoods of C4
		C4.setNeighbour(Direction.NORTH, C3);
		C4.setNeighbour(Direction.WEST, B4);
		C4.setNeighbour(Direction.SOUTH, C5);
		C4.setNeighbour(Direction.EAST, D4);
		// Handle neighborhoods of D4
		D4.setNeighbour(Direction.NORTH, D3);
		D4.setNeighbour(Direction.WEST, C4);
		D4.setNeighbour(Direction.SOUTH, D5);
		D4.setNeighbour(Direction.EAST, E4);
		// Handle neighborhoods of E4
		E4.setNeighbour(Direction.NORTH, E3);
		E4.setNeighbour(Direction.WEST, D4);
		E4.setNeighbour(Direction.SOUTH, E5);
		E4.setNeighbour(Direction.EAST, F4);
		// Handle neighborhoods of F4
		F4.setNeighbour(Direction.NORTH, F3);
		F4.setNeighbour(Direction.WEST, E4);
		F4.setNeighbour(Direction.SOUTH, F5);

		/*
		 * FIFTH row
		 */
		// Handle neighborhoods of A5
		A5.setNeighbour(Direction.NORTH, A4);
		A5.setNeighbour(Direction.EAST, B5);
		A5.setNeighbour(Direction.SOUTH, A6);
		// Handle neighborhoods of B5
		B5.setNeighbour(Direction.NORTH, B4);
		B5.setNeighbour(Direction.WEST, A5);
		B5.setNeighbour(Direction.SOUTH, B6);
		B5.setNeighbour(Direction.EAST, C5);
		// Handle neighborhoods of C5
		C5.setNeighbour(Direction.NORTH, C4);
		C5.setNeighbour(Direction.WEST, B5);
		C5.setNeighbour(Direction.SOUTH, C6);
		C5.setNeighbour(Direction.EAST, D5);
		// Handle neighborhoods of D5
		D5.setNeighbour(Direction.NORTH, D4);
		D5.setNeighbour(Direction.WEST, C5);
		D5.setNeighbour(Direction.SOUTH, D6);
		D5.setNeighbour(Direction.EAST, E5);
		// Handle neighborhoods of E5
		E5.setNeighbour(Direction.NORTH, E4);
		E5.setNeighbour(Direction.WEST, D5);
		E5.setNeighbour(Direction.SOUTH, E6);
		E5.setNeighbour(Direction.EAST, F5);
		// Handle neighborhoods of F5
		F5.setNeighbour(Direction.NORTH, F4);
		F5.setNeighbour(Direction.WEST, E5);
		F5.setNeighbour(Direction.SOUTH, F6);

		/*
		 * SIXTH row
		 */
		// Handle neighborhoods of A6
		A6.setNeighbour(Direction.NORTH, A5);
		A6.setNeighbour(Direction.EAST, B6);
		// Handle neighborhoods of B6
		B6.setNeighbour(Direction.WEST, A6);
		B6.setNeighbour(Direction.NORTH, B5);
		B6.setNeighbour(Direction.EAST, C6);
		// Handle neighborhoods of C6
		C6.setNeighbour(Direction.WEST, B6);
		C6.setNeighbour(Direction.NORTH, C5);
		C6.setNeighbour(Direction.EAST, D6);
		// Handle neighborhoods of D6
		D6.setNeighbour(Direction.WEST, C6);
		D6.setNeighbour(Direction.NORTH, D5);
		D6.setNeighbour(Direction.EAST, E6);
		// Handle neighborhoods of E6
		E6.setNeighbour(Direction.WEST, D6);
		E6.setNeighbour(Direction.NORTH, E5);
		E6.setNeighbour(Direction.EAST, F6);
		// Handle neighborhoods of F6
		F6.setNeighbour(Direction.WEST, E6);
		F6.setNeighbour(Direction.NORTH, F5);
	}

	/*
	 * Build the map and store a reference to the instantiated player and store
	 * the instantiated ZPMs in the set
	 */
	public void buildSkeletonMap() {
		// Initialize ZPM
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
		SpecialWall southSouth = new SpecialWall();

		// Initialize player, set box, zpm, door
		player = new Player(middle, Direction.WEST);
		west.setDoor(east);
		northEast.setZpm(zpm);
		Box box = new Box(middle);
		box.arriveOnMapElement(null, middle);

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
		south.setNeighbour(Direction.SOUTH, southSouth);

		// Handle neighborhoods of southEast
		southEast.setNeighbour(Direction.NORTH, east);
		southEast.setNeighbour(Direction.WEST, south);

		// Handle neighborhoods of southSouth
		southSouth.setNeighbour(Direction.NORTH, south);
	}

	public Controller createController(Game game) {
		if (game == null || player == null || zpmSet.isEmpty())
			return null;

		return new Controller(game, player, zpmSet);
	}
}
