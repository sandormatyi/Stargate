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
			br = new BufferedReader(new InputStreamReader(System.in));
			String choice;

			while (true) {
				printMainMenu();
				choice = br.readLine();

				switch (choice) {
				case "1":
					printPlayerMovamentMenu();
					choice = br.readLine();
					handlePlayerTestCall(choice);
					br.readLine();
					break;
				case "2":
					printBoxInteractionMenu();
					choice = br.readLine();
					handleBoxTestCall(choice);
					br.readLine();
					break;
				case "3":
					printShootMenu();
					choice = br.readLine();
					handleShootTestCall(choice);
					br.readLine();
					break;
				case "4":
					printZpmMenu();
					choice = br.readLine();
					handleZpmTestCall(choice);
					br.readLine();
					break;
				case "5":
					System.exit(0);
				default:
					System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
					System.out.println("A lehet�s�geid:");
					break;
				}
			}
		} catch (IOException io) {
			System.err.println("ERROR: A rendszerben kiv�tel keletkezett, k�rem ind�tsa �jra az alkalmaz�st!");
		}
	}

	/*
	 * Handle ZPM test calls.
	 */
	private static void handleZpmTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			System.out.println("Megh�vtam: Utols� ZPM felv�tele");
			TestRunner.runTest(TestType.ZPM);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
			printZpmMenu();
			newChoice = br.readLine();
			handleZpmTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle shoot test calls.
	 */
	private static void handleShootTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			System.out.println("Megh�vtam: L�v�s falra");
			TestRunner.runTest(TestType.ShootWall);
			break;
		case "2":
			System.out.println("Megh�vtam: L�v�s speci�lis falra");
			TestRunner.runTest(TestType.ShootSpecialWall);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
			printShootMenu();
			newChoice = br.readLine();
			handleShootTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle box test calls.
	 */
	private static void handleBoxTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			System.out.println("Megh�vtam: Felv�tel �tr�l");
			TestRunner.runTest(TestType.BoxPickUpRoad);
			break;
		case "2":
			System.out.println("Megh�vtam: Felv�tel m�rlegr�l");
			TestRunner.runTest(TestType.BoxPickUpScale);
			break;
		case "3":
			System.out.println("Megh�vtam: Felv�tel port�lon �tny�lva");
			TestRunner.runTest(TestType.BoxPickUpWormhole);
			break;
		case "4":
			System.out.println("Megh�vtam: Lerak�s �tra");
			TestRunner.runTest(TestType.BoxPutDownRoad);
			break;
		case "5":
			System.out.println("Megh�vtam: Lerak�s m�rlegre");
			TestRunner.runTest(TestType.BoxPutDownScale);
			break;
		case "6":
			System.out.println("Megh�vtam: Lerak�s szakad�kba");
			TestRunner.runTest(TestType.BoxPutDownGap);
			break;
		case "7":
			System.out.println("Megh�vtam: Lerak�s falra");
			TestRunner.runTest(TestType.BoxPutDownWall);
			break;
		case "8":
			System.out.println("Megh�vtam: Lerak�s port�lon kereszt�l");
			TestRunner.runTest(TestType.BoxPutDownWormhole);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
			printBoxInteractionMenu();
			newChoice = br.readLine();
			handleBoxTestCall(newChoice);
			break;
		}
	}

	/*
	 * Handle player test calls.
	 */
	private static void handlePlayerTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			System.out.println("Megh�vtam: J�t�kos mozgat�sa �tra");
			TestRunner.runTest(TestType.PlayerMoveRoad);
			break;
		case "2":
			System.out.println("Megh�vtam: J�t�kos mozgat�sa m�rlegre");
			TestRunner.runTest(TestType.PlayerMoveScale);
			break;
		case "3":
			System.out.println("Megh�vtam: J�t�kos mozgat�sa falra");
			TestRunner.runTest(TestType.PlayerMoveWall);
			break;
		case "4":
			System.out.println("Megh�vtam: J�t�kos mozgat�sa speci�lis falra (nyitott port�l)");
			TestRunner.runTest(TestType.PlayerMoveWormhole);
			break;
		case "5":
			System.out.println("Megh�vtam: J�t�kos mozgat�sa szakad�kba");
			TestRunner.runTest(TestType.PlayerMoveGap);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelel� men�pontot v�lasztott�l ki!");
			System.out.println("A lehet�s�geid:");
			printPlayerMovamentMenu();
			newChoice = br.readLine();
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
	}

	/*
	 * Print shoot menu.
	 */
	private static void printShootMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) L�v�s falra");
		System.out.println("(2) L�v�s speci�lis falra");
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
	}
}
