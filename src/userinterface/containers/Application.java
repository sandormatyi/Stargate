package userinterface.containers;

import javax.swing.JFrame;

import controller.GameRunner;
import test.TestType;

public class Application extends JFrame {

	/*
	 * Initializes the application window
	 */
	public Application() {
		add(new GamePanel());

		pack();

		setSize(500, 500);

		setTitle("Stargate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		GameRunner.runTest(TestType.PlayerMoveWormhole);

		invalidate();
	}

	/*
	 * Returns the scale of the game
	 */
	public static int getScale() {
		return 32;
	}

}
