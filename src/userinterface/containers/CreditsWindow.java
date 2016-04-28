package userinterface.containers;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreditsWindow extends JFrame {

	public CreditsWindow() {
		// The background panel of the window
		setContentPane(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Display the background image of the main menu
				g.drawImage(new ImageIcon("images/credits.png").getImage(), 0, 0, null);
			}
		});

		pack();

		setResizable(false);

		setTitle("Credits");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(830, 480);
	}
}
