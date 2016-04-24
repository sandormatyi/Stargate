package userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import debug.ProtoLogger;
import test.PrototypeValidator;
import test.TestRunner;
import test.TestType;

public class Main {

	public static void main(String[] args) {
		try {
			PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
			System.setOut(outStream);
			ProtoLogger.redirectOutput(outStream);

			System.setErr(new PrintStream(System.err, true, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			System.err.println(
					"The standard output does not support UTF-8 encoding: some characters may appear incorrectly");
		}

		BufferedReader br = null;
		try {
			// Reads from standard in
			br = new BufferedReader(new InputStreamReader(System.in));
			int choice;

			// Endless loop for handling menu
			while (true) {
				try {
					// Prints main menu, read user's choice
					printMainMenu();
					choice = Integer.parseInt(br.readLine());

					// Handles user's choice
					switch (choice) {
					// Prints movament menu, read user's choice, then handles it
					case 1:
						printPlayerMovamentMenu();
						choice = Integer.parseInt(br.readLine());
						handlePlayerTestCall(choice);
						br.readLine();
						break;
					// Prints box interaction menu, read user's choice, then
					// handles
					// it
					case 2:
						printBoxInteractionMenu();
						choice = Integer.parseInt(br.readLine());
						handleBoxTestCall(choice);
						br.readLine();
						break;
					// Prints shoot menu, read user's choice, then handles it
					case 3:
						printShootMenu();
						choice = Integer.parseInt(br.readLine());
						handleShootTestCall(choice);
						br.readLine();
						break;
					// Prints zpm menu, read user's choice, then handles it
					case 4:
						printZpmMenu();
						choice = Integer.parseInt(br.readLine());
						handleZpmTestCall(choice);
						br.readLine();
						break;
					case 5:
						printReplicatorMenu();
						choice = Integer.parseInt(br.readLine());
						handleReplicatorTestCall(choice);
						br.readLine();
						break;
					case 6:
						System.out.println("-------------------------------------------------------------");
						System.out.println("Egyik játékos megöli a másikat");
						System.out.println("-------------------------------------------------------------");
						TestRunner.runTest(TestType.PlayerKillsOtherPlayer);
						br.readLine();
						break;
					case 7:
						System.out.println("-------------------------------------------------------------");
						System.out.println("Összes teszteset lefuttatása és ellenőrzése");
						System.out.println("-------------------------------------------------------------");
						new PrototypeValidator().runAllTestsAndCompareResults();
						br.readLine();
						break;
					// Exits from program
					case 8:
						System.exit(0);
						break;
					// Handles wrong input
					default:
						System.out.println("Nem megfelelő menüpontot választottál ki!");
						System.out.println("A lehetőségeid:");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Kérlek, üss be egy számot!");
				}
			}
		} catch (IOException io) {
			System.err.println("ERROR: A rendszerben kivétel keletkezett, kérem indítsa újra az alkalmazást!");
		} catch (Exception e) {
			System.err.println("ERROR: A rendszerben kivétel keletkezett, kérem indítsa újra az alkalmazást!");
		}
	}

	/*
	 * Handle Replicator test calls.
	 */
	private static void handleReplicatorTestCall(int choice) throws IOException {
		switch (choice) {
		case 1:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Replikátor szakadékba esik");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ReplicatorMoveGap);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelelő menüpontot választottál ki!");
			System.out.println("A lehetőségeid:");
			printReplicatorMenu();
			newChoice = Integer.parseInt(br.readLine());
			handleReplicatorTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle ZPM test calls.
	 */
	private static void handleZpmTestCall(int choice) throws IOException {
		switch (choice) {
		// Handles Zpm pickup test
		case 1:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Utolsó ZPM felvétele");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.LastZPM);
			break;
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Két ZPM felvétele");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.TwoZPMs);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelelő menüpontot választottál ki!");
			System.out.println("A lehetőségeid:");
			printZpmMenu();
			newChoice = Integer.parseInt(br.readLine());
			handleZpmTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle shoot test calls.
	 */
	private static void handleShootTestCall(int choice) throws IOException {
		switch (choice) {
		// Handles shoot on wall test
		case 1:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lövés falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ShootWall);
			break;
		// Handles shoot on specialwall test
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lövés speciális falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ShootSpecialWall);
			break;
		case 3:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lövés replikátorra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ShootReplicator);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelelő menüpontot választottál ki!");
			System.out.println("A lehetőségeid:");
			printShootMenu();
			newChoice = Integer.parseInt(br.readLine());
			handleShootTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle box test calls.
	 */
	private static void handleBoxTestCall(int choice) throws IOException {
		switch (choice) {
		// Handles pick up from road
		case 1:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Felvétel útról");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpRoad);
			break;
		// Handles pick up from scale
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Felvétel mérlegről");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpScale);
			break;
		// Handles pick up through wormhole
		case 3:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Felvétel portálon átnyúlva");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpWormhole);
			break;
		// Handles put down (road)
		case 4:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerakás útra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownRoad);
			break;
		// Handles put down (scale)
		case 5:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerakás mérlegre");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownScale);
			break;
		// Handles put down (gap)
		case 6:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerakás szakadékba");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownGap);
			break;
		// Handles put down (wall)
		case 7:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerakás falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownWall);
			break;
		// Handles put down (through wormhole)
		case 8:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerakás portálon keresztül");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownWormhole);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelelő menüpontot választottál ki!");
			System.out.println("A lehetőségeid:");
			printBoxInteractionMenu();
			newChoice = Integer.parseInt(br.readLine());
			handleBoxTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle player test calls.
	 */
	private static void handlePlayerTestCall(int choice) throws IOException {
		switch (choice) {
		// Handles player move to road
		case 1:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Játékos mozgatása útra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveRoad);
			break;
		// Handles player move to scale
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Játékos mozgatása mérlegre");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveScale);
			break;
		// Handles player move to wall
		case 3:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Játékos mozgatása falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveWall);
			break;
		// Handles player move to portal (opened)
		case 4:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Játékos mozgatása speciális falra (nyitott portál)");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveWormhole);
			break;
		// Handles player move to gap
		case 5:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Játékos mozgatása szakadékba");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveGap);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelelő menüpontot választottál ki!");
			System.out.println("A lehetőségeid:");
			printPlayerMovamentMenu();
			newChoice = Integer.parseInt(br.readLine());
			handlePlayerTestCall(newChoice);
			break;
		}
	}

	/*
	 * Print Replicator menu.
	 */
	private static void printReplicatorMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Replikátor szakadékba esik");
		System.out.println("*************************************************************");
	}

	/*
	 * Print ZPM menu.
	 */
	private static void printZpmMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Utolsó ZPM felvétele");
		System.out.println("(2) Két ZPM felvétele");
		System.out.println("*************************************************************");
	}

	/*
	 * Print shoot menu.
	 */
	private static void printShootMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Lövés falra");
		System.out.println("(2) Lövés speciális falra");
		System.out.println("(3) Lövés replikátorra");
		System.out.println("*************************************************************");
	}

	/*
	 * Print box menu.
	 */
	private static void printBoxInteractionMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Felvétel útról");
		System.out.println("(2) Felvétel mérlegről");
		System.out.println("(3) Felvétel portálon átnyúlva");
		System.out.println("(4) Lerakás útra");
		System.out.println("(5) Lerakás mérlegre");
		System.out.println("(6) Lerakás szakadékba");
		System.out.println("(7) Lerakás falra");
		System.out.println("(8) Lerakás portálon keresztül");
		System.out.println("*************************************************************");
	}

	/*
	 * Print player menu.
	 */
	private static void printPlayerMovamentMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Játékos mozgatása útra");
		System.out.println("(2) Játékos mozgatása mérlegre");
		System.out.println("(3) Játékos mozgatása falra");
		System.out.println("(4) Játékos mozgatása speciális falra (nyitott portál)");
		System.out.println("(5) Játékos mozgatása szakadékba");
		System.out.println("*************************************************************");
	}

	/*
	 * Print main menu.
	 */
	private static void printMainMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Játékos mozgatása");
		System.out.println("(2) Doboz interakció");
		System.out.println("(3) Lövés");
		System.out.println("(4) ZPM felvétele");
		System.out.println("(5) Replikátor mozgatása");
		System.out.println("(6) Egyik játékos megöli a másikat");
		System.out.println("(7) Összes teszteset lefuttatása és ellenőrzése");
		System.out.println("(8) Kilépés");
		System.out.println("*************************************************************");
	}
}
