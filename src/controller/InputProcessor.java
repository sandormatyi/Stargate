package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;

import debug.ProtoLogger;
import debug.RandomGenerator;
import gamemodel.Direction;

public class InputProcessor {
	/*
	 * The game that recevies the input
	 */
	private Game game;

	/*
	 * The controller that receives the commands
	 */
	private Controller controller;

	/*
	 * Constructor function
	 */
	public InputProcessor(Game game) {
		this.game = game;
		this.controller = game.getController();
	}

	/*
	 * Reads and processes the input file
	 */
	public void processInputFile(String inputFilePath) throws IOException {
		URL resURL = getClass().getClassLoader().getResource(inputFilePath);

		if (resURL == null)
			throw new FileNotFoundException("Nem sikerült a megadott bementi fájl beolvasása: " + inputFilePath);

		FileReader input = new FileReader(resURL.getPath());
		BufferedReader bufRead = new BufferedReader(input);

		try {
			// Parse the rows of the input file and process them
			while (true) {
				String inputLine = bufRead.readLine();

				if (inputLine == null)
					break;

				try {
					processLine(inputLine);
				} catch (InvalidParameterException e) {
					ProtoLogger.logError(e.getMessage());
				}
			}
		} finally {
			bufRead.close();
		}
	}

	/*
	 * Processes a string and gives the appropriate commands to the controller
	 */
	public void processLine(String inputLine) {
		String[] words = inputLine.toLowerCase().split(" ");

		if (words[0].equals("restart")) {
			game.stop(true);
			game.run();

			controller = game.getController();
		} else if (words[0].equals("oneill")) {
			if (words.length == 1) {
				// TODO: Print status string
			} else {
				if (words[1].equals("proj1")) {
					controller.shootFirst(PlayerType.ONeill);
				} else if (words[1].equals("proj2")) {
					controller.shootSecond(PlayerType.ONeill);
				} else if (words[1].equals("pickup")) {
					controller.pickUpBox(PlayerType.ONeill);
				} else if (words[1].equals("putdown")) {
					controller.putDownBox(PlayerType.ONeill);
				} else {
					Direction dir = Direction.fromString(words[1]);

					if (dir != null) {
						controller.moveOrTurnPlayer(PlayerType.ONeill, dir);
					} else {
						throw new InvalidParameterException("Nem értelmezhető parancs: " + inputLine);
					}
				}
			}
		} else if (words[0].equals("jaffa")) {
			if (words.length == 1) {
				// TODO: Print status string
			} else {
				if (words[1].equals("proj1")) {
					controller.shootFirst(PlayerType.Jaffa);
				} else if (words[1].equals("proj2")) {
					controller.shootSecond(PlayerType.Jaffa);
				} else if (words[1].equals("pickup")) {
					controller.pickUpBox(PlayerType.Jaffa);
				} else if (words[1].equals("putdown")) {
					controller.putDownBox(PlayerType.Jaffa);
				} else {
					Direction dir = Direction.fromString(words[1]);

					if (dir != null) {
						controller.moveOrTurnPlayer(PlayerType.Jaffa, dir);
					} else {
						throw new InvalidParameterException("Nem értelmezhető parancs: " + inputLine);
					}
				}
			}
		} else if (words[0].equals("replicator")) {
			if (words.length == 1) {
				// TODO: Print status string
			} else {
				if (words[1].equals("random")) {
					controller.moveReplicatorUntilDeath();
				} else {
					Direction dir = Direction.fromString(words[1]);

					if (dir != null) {
						controller.moveOrTurnReplicator(dir);
					} else {
						throw new InvalidParameterException("Nem értelmezhető parancs: " + inputLine);
					}
				}
			}
		} else if (words[0].equals("zpm_random")) {
			if (words.length == 1) {
				throw new InvalidParameterException("Nem értelmezhető parancs: " + inputLine);
			} else {
				if (words[1].equals("on")) {
					RandomGenerator.enableRandom();
				} else if (words[1].equals("off")) {
					RandomGenerator.disableRandom();
				}
			}
		} else {
			throw new InvalidParameterException("Nem értelmezhető parancs: " + inputLine);
		}
	}
}
