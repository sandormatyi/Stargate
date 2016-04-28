package userinterface.containers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.events.ControllerEventSource;
import controller.events.IGameEventListener;
import gamemodel.Player;

public class Application extends JFrame implements IGameEventListener {

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
	 * Displays a dialog with the name of the winner
	 */
	@Override
	public void onGameOver(Player winner) {
		String messageString = (winner == null) ? "Game over, there is no winner"
				: "Game over, the winner is " + winner.toString();

		JOptionPane.showMessageDialog(this, messageString);
	}
}
