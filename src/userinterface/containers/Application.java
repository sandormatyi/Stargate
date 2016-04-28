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

		ControllerEventSource.subscribe(this);

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
		String messageString = (winner == null) ? "There is no winner" : "The winner is " + winner.toString();

		JOptionPane.showMessageDialog(this, messageString, "Game over", JOptionPane.PLAIN_MESSAGE);

		dispose();
	}
}
