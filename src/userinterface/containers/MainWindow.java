package userinterface.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import userinterface.UIUtility;

public class MainWindow extends JFrame {
	/*
	 * The custom button class that is used in the menu
	 */
	public class MenuButton extends JButton {
		public MenuButton(String string) {
			super(string);
		}

		@Override
		public Font getFont() {
			return UIUtility.getMenuButtonFont();
		}

		@Override
		public float getAlignmentX() {
			return CENTER_ALIGNMENT;
		}

		@Override
		public Color getBackground() {
			return Color.lightGray;
		}
	}

	/*
	 * Default constructor
	 */
	public MainWindow() {
		// The background panel of the window
		JPanel backgroundPanel = new JPanel() {
			/*
			 * The backround image of the window
			 */
			private Image backgroundImage = new ImageIcon("images/mainmenu.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Display the background image of the main menu
				g.drawImage(backgroundImage, 0, 0, null);
			}
		};
		backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
		setContentPane(backgroundPanel);

		// The button that starts a new game
		JButton newGameButton = new MenuButton("New game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// Create a new window for the game
						GameWindow gameWindow = new GameWindow();
						gameWindow.setVisible(true);

						// Close the main window
						MainWindow.this.dispose();
					}
				});
			}
		});

		// The button that shows the credits
		JButton creditsButton = new MenuButton("Credits");
		creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// Create a new window for the game
						CreditsWindow creditsWindow = new CreditsWindow();
						creditsWindow.setVisible(true);
					}
				});
			}
		});

		// The button that exits the application
		JButton exitButton = new MenuButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Add the buttons to the panel
		backgroundPanel.add(Box.createRigidArea(new Dimension(0, 32)));
		backgroundPanel.add(newGameButton);

		backgroundPanel.add(Box.createRigidArea(new Dimension(0, 224)));
		backgroundPanel.add(creditsButton);

		backgroundPanel.add(Box.createRigidArea(new Dimension(0, 32)));
		backgroundPanel.add(exitButton);

		pack();

		setResizable(false);

		setTitle("Stargate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(640, 480);
	}
}
