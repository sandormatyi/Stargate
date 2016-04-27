package userinterface.containers;

import javax.swing.JFrame;

import controller.events.ControllerEventSource;

public class Application extends JFrame {

	/*
	 * Initializes the application window
	 */
	public Application() {
		ControllerEventSource.clear();

		GamePanel panel = new GamePanel();
		panel.initialize();
		add(panel);

		pack();

		setSize(500, 500);
		setResizable(false);

		setTitle("Stargate");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/*
	 * Returns the scale of the game
	 */
	public static int getScale() {
		return 32;
	}

	/*
	 * Returns the delay between two steps of the projectile (in ms)
	 */
	public static int getProjectileDelay() {
		return 250;
	}

	public static int getInputDelay() {
		return 300;
	}
}
