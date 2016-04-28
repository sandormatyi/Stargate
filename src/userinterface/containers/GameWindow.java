package userinterface.containers;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.events.ControllerEventSource;
import controller.events.IGameEventListener;
import gamemodel.Player;

public class GameWindow extends JFrame implements IGameEventListener {
	/*
	 * Initializes the application window
	 */
	public GameWindow() {
		setLayout(new FlowLayout());

		SidePanel leftSidePanel = new SidePanel("images/oneill_static.png");
		add(leftSidePanel);

		GamePanel gamePanel = new GamePanel();
		add(gamePanel);

		SidePanel rightSidePanel = new SidePanel("images/jaffa_static.png");
		add(rightSidePanel);

		pack();

		setResizable(false);

		setTitle("Stargate");
		// TODO: Change to EXIT_ON_CLOSE
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		ControllerEventSource.clear();
		ControllerEventSource.subscribe(this);

		gamePanel.initialize();
	}

	/*
	 * Displays a dialog with the name of the winner
	 */
	@Override
	public void onGameOver(Player winner) {
		String messageString = (winner == null) ? "There is no winner" : "The winner is " + winner.toString();

		JOptionPane.showMessageDialog(this, messageString, "Game over", JOptionPane.PLAIN_MESSAGE);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow mainWindow = new MainWindow();
				mainWindow.setVisible(true);
			}
		});

		dispose();
	}
}
