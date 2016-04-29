package userinterface;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.SwingUtilities;

import debug.GameLogger;
import debug.UILogger;
import userinterface.containers.MainWindow;

public class Main {

	/*
	 * The entry point of the application
	 */
	public static void main(String[] args) {
		// Try to set the output encoding to UTF-8
		try {
			PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
			System.setOut(outStream);
			GameLogger.redirectOutput(outStream);
			UILogger.redirectOutput(outStream);

			System.setErr(new PrintStream(System.err, true, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			System.err.println(
					"The standard output does not support UTF-8 encoding: some characters may appear incorrectly");
		}

		// Show the main menu
		showMenu();
	}

	/*
	 * Shows the main menu window
	 */
	public static void showMenu() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow app = new MainWindow();
				app.setVisible(true);
			}
		});
	}
}
