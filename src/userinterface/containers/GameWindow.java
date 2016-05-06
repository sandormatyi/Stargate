package userinterface.containers;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.Controller;
import controller.GameRunner;
import controller.PlayerType;
import controller.events.ControllerEventSource;
import controller.events.IGameEventListener;
import userinterface.InputListener;
import userinterface.Main;

public class GameWindow extends JFrame implements IGameEventListener {
	/*
	 * Associative container for the side panels
	 */
	Map<PlayerType, SidePanel> sidePanels = new HashMap<PlayerType, SidePanel>();

	/*
	 * The Controller object for the current game
	 */
	Controller controller = null;

	/*
	 * Initializes the game window
	 */
	public GameWindow() {
		// Set the layout manager
		setLayout(new FlowLayout());

		// Create and add the panels of the window
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		ControllerEventSource.clear();
		ControllerEventSource.subscribe(this);

		gamePanel.initialize();

		// Start a new game and initalize the controls
		controller = GameRunner.startGame();
		addKeyListener(new InputListener(controller));

		// When the window is closed, return to the main menu
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				controller.gameOver();
				Main.showMenu();
			}
		});
	}

	/*
	 * Displays a dialog with the name of the winner
	 */
	@Override
	public void onGameOver(String winner) {
		String messageString = (winner == null) ? "There is no winner" : "The winner is " + winner;

		JOptionPane.showMessageDialog(this, messageString, "Game over", JOptionPane.PLAIN_MESSAGE,
				new ImageIcon("images/LZ.png"));

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
