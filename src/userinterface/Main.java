package userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import test.TestRunner;
import test.TestType;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			// Reads from standard in
			br = new BufferedReader(new InputStreamReader(System.in));
			int choice;

			// Endless loop for handling menu
			while (true) {
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
				// Prints box interaction menu, read user's choice, then handles
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
				// Exits from program
				case 5:
					System.exit(0);
					// Handles wrong input
				default:
					System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
					System.out.println("A lehet�s�geid:");
					break;
				}
			}
		} catch (IOException io) {
			System.err.println("ERROR: A rendszerben kiv�tel keletkezett, k�rem ind�tsa �jra az alkalmaz�st!");
		} catch (Exception e) {
			System.err.println("ERROR: A rendszerben kiv�tel keletkezett, k�rem ind�tsa �jra az alkalmaz�st!");
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
			System.out.println("Utols� ZPM felv�tele");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ZPM);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
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
			System.out.println("L�v�s falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ShootWall);
			break;
		// Handles shoot on specialwall test
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("L�v�s speci�lis falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.ShootSpecialWall);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
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
			System.out.println("Felv�tel �tr�l");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpRoad);
			break;
		// Handles pick up from scale
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Felv�tel m�rlegr�l");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpScale);
			break;
		// Handles pick up through wormhole
		case 3:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Felv�tel port�lon �tny�lva");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPickUpWormhole);
			break;
		// Handles put down (road)
		case 4:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerak�s �tra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownRoad);
			break;
		// Handles put down (scale)
		case 5:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerak�s m�rlegre");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownScale);
			break;
		// Handles put down (gap)
		case 6:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerak�s szakad�kba");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownGap);
			break;
		// Handles put down (wall)
		case 7:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerak�s falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownWall);
			break;
		// Handles put down (through wormhole)
		case 8:
			System.out.println("-------------------------------------------------------------");
			System.out.println("Lerak�s port�lon kereszt�l");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.BoxPutDownWormhole);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
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
			System.out.println("J�t�kos mozgat�sa �tra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveRoad);
			break;
		// Handles player move to scale
		case 2:
			System.out.println("-------------------------------------------------------------");
			System.out.println("J�t�kos mozgat�sa m�rlegre");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveScale);
			break;
		// Handles player move to wall
		case 3:
			System.out.println("-------------------------------------------------------------");
			System.out.println("J�t�kos mozgat�sa falra");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveWall);
			break;
		// Handles player move to portal (opened)
		case 4:
			System.out.println("-------------------------------------------------------------");
			System.out.println("J�t�kos mozgat�sa speci�lis falra (nyitott port�l)");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveWormhole);
			break;
		// Handles player move to gap
		case 5:
			System.out.println("-------------------------------------------------------------");
			System.out.println("J�t�kos mozgat�sa szakad�kba");
			System.out.println("-------------------------------------------------------------");
			TestRunner.runTest(TestType.PlayerMoveGap);
			break;
		// Handles wrong input, then prints menu again and reads new choice,
		// finally calls this method again with new choice
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int newChoice = 0;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
			printPlayerMovamentMenu();
			newChoice = Integer.parseInt(br.readLine());
			handlePlayerTestCall(newChoice);
			break;
		}
	}

	/*
	 * Print ZPM menu.
	 */
	private static void printZpmMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Utols� ZPM felv�tele");
		System.out.println("*************************************************************");
	}

	/*
	 * Print shoot menu.
	 */
	private static void printShootMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) L�v�s falra");
		System.out.println("(2) L�v�s speci�lis falra");
		System.out.println("*************************************************************");
	}

	/*
	 * Print box menu.
	 */
	private static void printBoxInteractionMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Felv�tel �tr�l");
		System.out.println("(2) Felv�tel m�rlegr�l");
		System.out.println("(3) Felv�tel port�lon �tny�lva");
		System.out.println("(4) Lerak�s �tra");
		System.out.println("(5) Lerak�s m�rlegre");
		System.out.println("(6) Lerak�s szakad�kba");
		System.out.println("(7) Lerak�s falra");
		System.out.println("(8) Lerak�s port�lon kereszt�l");
		System.out.println("*************************************************************");
	}

	/*
	 * Print player menu.
	 */
	private static void printPlayerMovamentMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) J�t�kos mozgat�sa �tra");
		System.out.println("(2) J�t�kos mozgat�sa m�rlegre");
		System.out.println("(3) J�t�kos mozgat�sa falra");
		System.out.println("(4) J�t�kos mozgat�sa speci�lis falra (nyitott port�l)");
		System.out.println("(5) J�t�kos mozgat�sa szakad�kba");
		System.out.println("*************************************************************");
	}

	/*
	 * Print main menu.
	 */
	private static void printMainMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) J�t�kos mozgat�sa");
		System.out.println("(2) Doboz interakci�");
		System.out.println("(3) L�v�s");
		System.out.println("(4) ZPM felv�tele");
		System.out.println("(5) Kil�p�s");
		System.out.println("*************************************************************");
	}
}
