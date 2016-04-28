package userinterface.containers;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.events.ControllerEventSource;
import controller.events.IGameEventListener;
import gamemodel.Player;

public class GameWindow extends JFrame implements IGameEventListener {
	/*
	 * The layout of the window
	 */
	FlowLayout flowLayout = new FlowLayout();

	/*
	 * Initializes the application window
	 */
	public GameWindow() {
		setLayout(flowLayout);

		SidePanel leftSidePanel = new SidePanel("images/oneill_static.png");
		add(leftSidePanel);

		GamePanel panel = new GamePanel();
		add(panel);

		SidePanel rightSidePanel = new SidePanel("images/jaffa_static.png");
		add(rightSidePanel);

		pack();

		setResizable(false);

		setTitle("Stargate");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		ControllerEventSource.clear();
		ControllerEventSource.subscribe(this);

		panel.initialize();
	}

	/*
	 * Displays a dialog with the name of the winner
	 */
	@Override
	public void onGameOver(Player winner) {
		String messageString = (winner == null) ? "There is no winner" : "The winner is " + winner.toString();

		JOptionPane.showMessageDialog(this, messageString, "Game over", JOptionPane.PLAIN_MESSAGE);

		// TODO: Go back to main menu

		dispose();
	}
}
