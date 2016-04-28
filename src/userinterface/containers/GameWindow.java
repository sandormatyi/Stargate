package userinterface.containers;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.PlayerType;
import controller.events.ControllerEventSource;
import controller.events.IGameEventListener;

public class GameWindow extends JFrame implements IGameEventListener {
	/*
	 * Associative container for the panels
	 */
	Map<PlayerType, SidePanel> sidePanels = new HashMap<PlayerType, SidePanel>();

	/*
	 * Initializes the application window
	 */
	public GameWindow() {
		setLayout(new FlowLayout());

		SidePanel leftSidePanel = new SidePanel(PlayerType.ONeill);
		sidePanels.put(PlayerType.ONeill, leftSidePanel);
		add(leftSidePanel);

		GamePanel gamePanel = new GamePanel();
		add(gamePanel);

		SidePanel rightSidePanel = new SidePanel(PlayerType.Jaffa);
		sidePanels.put(PlayerType.Jaffa, rightSidePanel);
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
	public void onGameOver(String winner) {
		String messageString = (winner == null) ? "There is no winner" : "The winner is " + winner;

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

	@Override
	public void onScoreIncreased(PlayerType player) {
		sidePanels.get(player).incrementScore();
	}
}
