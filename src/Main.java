import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
			System.out.println("ERROR: A rendszerben kivétel keletkezett, kérem indítsa újra az alkalmazást!");
		}
	}

	private static void handleZpmTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			// TODO:call test
			System.out.println("Meghívtam: Utolsó ZPM felvétele");
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

	private static void handleShootTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			// TODO:call test
			System.out.println("Meghívtam: Lövés falra");
			break;
		case "2":
			// TODO:call test
			System.out.println("Meghívtam: Lövés speciális falra");
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

	private static void handleBoxTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			// TODO:call test
			System.out.println("Meghívtam: Felvétel útról");
			break;
		case "2":
			// TODO:call test
			System.out.println("Meghívtam: Felvétel mérlegrõl");
			break;
		case "3":
			// TODO:call test
			System.out.println("Meghívtam: Felvétel portálon átnyúlva");
			break;
		case "4":
			// TODO:call test
			System.out.println("Meghívtam: Lerakás útra");
			break;
		case "5":
			// TODO:call test
			System.out.println("Meghívtam: Lerakás mérlegre");
			break;
		case "6":
			// TODO:call test
			System.out.println("Meghívtam: Lerakás szakadékba");
			break;
		case "7":
			// TODO:call test
			System.out.println("Meghívtam: Lerakás falra");
			break;
		case "8":
			// TODO:call test
			System.out.println("Meghívtam: Lerakás portálon keresztül");
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

	private static void handlePlayerTestCall(String choice) throws IOException {
		switch (choice) {
		case "1":
			// TODO:call test
			System.out.println("Meghívtam: Játékos mozgatása útra");
			break;
		case "2":
			// TODO:call test
			System.out.println("Meghívtam: Játékos mozgatása mérlegre");
			break;
		case "3":
			// TODO:call test
			System.out.println("Meghívtam: Játékos mozgatása falra");
			break;
		case "4":
			// TODO:call test
			System.out.println("Meghívtam: Játékos mozgatása speciális falra (nyitott portál)");
			break;
		case "5":
			// TODO:call test
			System.out.println("Meghívtam: Játékos mozgatása szakadékba");
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

	private static void printZpmMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Utolsó ZPM felvétele");
	}

	private static void printShootMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Lövés falra");
		System.out.println("(2) Lövés speciális falra");
	}

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

	private static void printPlayerMovamentMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Játékos mozgatása útra");
		System.out.println("(2) Játékos mozgatása mérlegre");
		System.out.println("(3) Játékos mozgatása falra");
		System.out.println("(4) Játékos mozgatása speciális falra (nyitott portál)");
		System.out.println("(5) Játékos mozgatása szakadékba");
	}

	private static void printMainMenu() {
		System.out.println("*************************************************************");
		System.out.println("(1) Játékos mozgatása");
		System.out.println("(2) Doboz interakció");
		System.out.println("(3) Lövés");
		System.out.println("(4) ZPM felvétele");
		System.out.println("(5) Kilépés");
	}
}
