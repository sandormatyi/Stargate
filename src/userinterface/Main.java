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
					System.out.println("Nem megfelelõ menüpontot választottál ki!");
					System.out.println("A lehetõségeid:");
					break;
				}
			}
		} catch (IOException io) {
			System.err.println("ERROR: A rendszerben kivétel keletkezett, kérem indítsa újra az alkalmazást!");
		}
	}

	/*
	 * Handle ZPM test calls.
	 */
	private static void handleZpmTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			System.out.println("Meghívtam: Utolsó ZPM felvétele");
			TestRunner.runTest(TestType.ZPM);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelelõ menüpontot választottál ki!");
			System.out.println("A lehetõségeid:");
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
			System.out.println("Meghívtam: Lövés falra");
			TestRunner.runTest(TestType.ShootWall);
			break;
		case "2":
			System.out.println("Meghívtam: Lövés speciális falra");
			TestRunner.runTest(TestType.ShootSpecialWall);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelelõ menüpontot választottál ki!");
			System.out.println("A lehetõségeid:");
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
			System.out.println("Meghívtam: Felvétel útról");
			TestRunner.runTest(TestType.BoxPickUpRoad);
			break;
		case "2":
			System.out.println("Meghívtam: Felvétel mérlegrõl");
			TestRunner.runTest(TestType.BoxPickUpScale);
			break;
		case "3":
			System.out.println("Meghívtam: Felvétel portálon átnyúlva");
			TestRunner.runTest(TestType.BoxPickUpWormhole);
			break;
		case "4":
			System.out.println("Meghívtam: Lerakás útra");
			TestRunner.runTest(TestType.BoxPutDownRoad);
			break;
		case "5":
			System.out.println("Meghívtam: Lerakás mérlegre");
			TestRunner.runTest(TestType.BoxPutDownScale);
			break;
		case "6":
			System.out.println("Meghívtam: Lerakás szakadékba");
			TestRunner.runTest(TestType.BoxPutDownGap);
			break;
		case "7":
			System.out.println("Meghívtam: Lerakás falra");
			TestRunner.runTest(TestType.BoxPutDownWall);
			break;
		case "8":
			System.out.println("Meghívtam: Lerakás portálon keresztül");
			TestRunner.runTest(TestType.BoxPutDownWormhole);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelelõ menüpontot választottál ki!");
			System.out.println("A lehetõségeid:");
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
			System.out.println("Meghívtam: Játékos mozgatása útra");
			TestRunner.runTest(TestType.PlayerMoveRoad);
			break;
		case "2":
			System.out.println("Meghívtam: Játékos mozgatása mérlegre");
			TestRunner.runTest(TestType.PlayerMoveScale);
			break;
		case "3":
			System.out.println("Meghívtam: Játékos mozgatása falra");
			TestRunner.runTest(TestType.PlayerMoveWall);
			break;
		case "4":
			System.out.println("Meghívtam: Játékos mozgatása speciális falra (nyitott portál)");
			TestRunner.runTest(TestType.PlayerMoveWormhole);
			break;
		case "5":
			System.out.println("Meghívtam: Játékos mozgatása szakadékba");
			TestRunner.runTest(TestType.PlayerMoveGap);
			break;
		default:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String newChoice = null;
			System.out.println("Nem megfelelõ menüpontot választottál ki!");
			System.out.println("A lehetõségeid:");
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
		System.out.println("(1) Utolsó ZPM felvétele");
	}

	/*
	 * Print shoot menu.
	 */
	private static void printShootMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Lövés falra");
		System.out.println("(2) Lövés speciális falra");
	}

	/*
	 * Print box menu.
	 */
	private static void printBoxInteractionMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Felvétel útról");
		System.out.println("(2) Felvétel mérlegrõl");
		System.out.println("(3) Felvétel portálon átnyúlva");
		System.out.println("(4) Lerakás útra");
		System.out.println("(5) Lerakás mérlegre");
		System.out.println("(6) Lerakás szakadékba");
		System.out.println("(7) Lerakás falra");
		System.out.println("(8) Lerakás portálon keresztül");
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
		System.out.println("(5) Kilépés");
	}
}
