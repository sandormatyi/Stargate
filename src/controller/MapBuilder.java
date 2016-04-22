package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import gamemodel.Box;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.Gap;
import gamemodel.Jaffa;
import gamemodel.MapElement;
import gamemodel.ONeill;
import gamemodel.Player;
import gamemodel.Replicator;
import gamemodel.Road;
import gamemodel.Scale;
import gamemodel.SpecialWall;
import gamemodel.Wall;
import gamemodel.ZPM;

class MapBuilder {

	private HashSet<ZPM> zpmSet = new HashSet<ZPM>();

	private Player oneill;
	private Player jaffa;
	private Replicator replicator;

	/*
	 * Exception class that is used to be thrown during the parsing of the map
	 * file
	 */
	public class MapBuilderException extends Exception {
		/*
		 * Stores the coordinate where the exception occured
		 */
		String coord;

		public MapBuilderException(String exception, String coord) {
			super(exception);

			this.coord = coord;
		}

		@Override
		public String toString() {
			return super.toString() + coord == null ? "" : " A hiba helye: " + coord;
		}
	}

	/*
	 * Reads the given map file from the /res/maps folder and if it is a valid
	 * map file, builds the map
	 */
	public void buildMapFromFile(String mapFilePath) throws IOException, MapBuilderException {
		URL resURL = getClass().getClassLoader().getResource(mapFilePath);

		if (resURL == null)
			throw new FileNotFoundException("Nem sikerült a megadott pálya beolvasása: " + mapFilePath);

		// A matrix for the temporary storage of the MapElements
		List<List<MapElement>> mapElements = new ArrayList<List<MapElement>>();

		// Temporarily store the scales and the doors by their index to be able
		// to pair them later
		HashMap<Integer, Scale> scales = new HashMap<Integer, Scale>();
		HashMap<Integer, Door> doors = new HashMap<Integer, Door>();

		// Store the roads to initialize MapHelper
		Vector<Road> roads = new Vector<Road>();

		FileReader input = new FileReader(resURL.getPath());
		BufferedReader bufRead = new BufferedReader(input);

		try {
			String rawLine = null;

			// Keep track of the current row index
			char rowIndex = 'A';

			// Parse the fields of the map and store the in the temporary matrix
			while ((rawLine = bufRead.readLine()) != null) {
				String[] processedLineArray = rawLine.replaceAll("[ \t]", "").split(",");

				List<MapElement> mapElementRow = new ArrayList<MapElement>(processedLineArray.length);

				int columnIndex = 1;

				for (String mapElementString : processedLineArray) {
					// Store the MapElement described by the map file entry
					String coord = String.format("%c%d", rowIndex, columnIndex);

					String[] mapParts = mapElementString.split("_");
					String mapElementType = mapParts[0].toUpperCase();

					MapElement mapElement = null;

					// Create the MapElement of the appropriate type
					if (mapElementType.equals("R")) {
						mapElement = new Road(coord);
						roads.addElement((Road) mapElement);
					} else if (mapElementType.equals("W")) {
						mapElement = new Wall(coord);
					} else if (mapElementType.equals("G")) {
						mapElement = new Gap(coord);
					} else if (mapElementType.equals("SW")) {
						mapElement = new SpecialWall(coord);
					} else {
						// The element is either a door or a scale
						String type = mapElementType.split(":")[0];

						if (type.equals("D")) {
							mapElement = new Door(coord);
							doors.put(Integer.parseInt(mapElementType.split(":")[1]), (Door) mapElement);
						} else if (type.equals("S")) {
							mapElement = new Scale(coord);
							scales.put(Integer.parseInt(mapElementType.split(":")[1]), (Scale) mapElement);
						}
					}

					if (mapElement == null)
						throw new MapBuilderException("A pályalem leírása hibás: " + mapElementString + ".", coord);

					// Check the second part of the map file entry for
					// additional objects on the field
					if (mapParts.length > 1) {
						char[] extraObjects = mapParts[1].toUpperCase().toCharArray();

						for (int i = 0; i < extraObjects.length; i++) {
							switch (extraObjects[i]) {
							case 'B':
								Box box = new Box(mapElement);
								try {
									box.arriveOnMapElement(null, mapElement);
								} catch (Exception e) {
									throw new MapBuilderException("Az adott mezőre nem lehet dobozt letenni!", coord);
								}
								break;
							case 'O':
								if (oneill != null)
									throw new MapBuilderException("A pályán több ONeill ezredes található!", coord);

								oneill = new ONeill(mapElement);
								oneill.arriveOnMapElement(null, mapElement);
								break;
							case 'J':
								if (jaffa != null)
									throw new MapBuilderException("A pályán több Jaffa található!", coord);

								jaffa = new Jaffa(mapElement);
								jaffa.arriveOnMapElement(null, mapElement);
								break;
							case 'Z':
								ZPM zpm = new ZPM();

								// No other way to check type
								if (!(mapElement instanceof Road))
									throw new MapBuilderException("ZPM csak út típusú mezőn lehet!", coord);

								((Road) mapElement).setZpm(zpm);
								zpmSet.add(zpm);
								break;
							case 'R':
								if (replicator != null)
									throw new MapBuilderException("A pályán több replikátor található!", coord);

								replicator = new Replicator(mapElement);
								replicator.arriveOnMapElement(null, mapElement);
								break;
							default:
								throw new MapBuilderException("Ismeretlen pályaelem: " + extraObjects[i], coord);
							}
						}
					}

					mapElementRow.add(mapElement);
					columnIndex++;
				}

				mapElements.add(mapElementRow);
				rowIndex++;
			}

			// Check if the pairing of the scales and the doors is possible
			for (int index : doors.keySet()) {
				if (!scales.containsKey(index))
					throw new MapBuilderException("Az ajtóhoz nem tartozik mérleg! Index: " + index, null);
			}

			for (int index : scales.keySet()) {
				if (!doors.containsKey(index))
					throw new MapBuilderException("A mérleghez nem tartozik ajtó! Index: " + index, null);
			}

			// Pair the doors and scales on the map
			for (int index : doors.keySet()) {
				Door door = doors.get(index);
				Scale scale = scales.get(index);

				scale.setDoor(door);
			}

			// Initialize MapHelper
			MapHelper.setRoads(roads);

			// Iterate through the stored MapElements and set their neighbours
			for (int y = 0; y < mapElements.size(); y++) {
				List<MapElement> currentRow = mapElements.get(y);

				for (int x = 1; x < currentRow.size(); x++) {
					MapElement currentElement = currentRow.get(x);

					// Set the horizontal neighbours
					MapElement westNeighbour = currentRow.get(x - 1);
					currentElement.setNeighbour(Direction.WEST, westNeighbour);
					westNeighbour.setNeighbour(Direction.EAST, currentElement);

					// Set the vertical neighbours
					if (y > 0) {
						List<MapElement> previousRow = mapElements.get(y - 1);

						if (!(previousRow.size() < x)) {
							MapElement northNeighbour = previousRow.get(x);
							currentElement.setNeighbour(Direction.NORTH, northNeighbour);
							northNeighbour.setNeighbour(Direction.SOUTH, currentElement);
						} else {
							String coord = String.format("%c%d", 'A' + y, x + 1);
							throw new MapBuilderException("Szabálytalan alakú pálya!", coord);
						}
					}
				}
			}
		} catch (MapBuilderException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			bufRead.close();
		}
	}

	/*
	 * Creates an instance of the Controller class for the current game
	 */
	public Controller createController(Game game) {
		if (game == null || (oneill == null && jaffa == null))
			return null;

		return new Controller(game, zpmSet, oneill, jaffa, replicator);
	}
}
