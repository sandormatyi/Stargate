package userinterface.containers;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CreditsWindow extends JFrame {
	/*
	 * The alpha of the foreground image
	 */
	private float imageAlpha = 0f;

	/*
	 * The timer for the animation
	 */
	private Timer timer;

	public CreditsWindow() {
		// The background panel of the window
		setContentPane(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2d = (Graphics2D) g;

				// Display the background image of the main menu
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1));
				g.drawImage(new ImageIcon("images/credits.png").getImage(), 0, 0, null);

				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageAlpha));
				g2d.drawImage(new ImageIcon("images/LZ.png").getImage(), 415 - 96, 480 - 192, null);

				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1));
			}
		});

		timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imageAlpha < 0.95) {
					imageAlpha += 0.05;

					timer.restart();
					getContentPane().repaint();
				}
			}
		});

		timer.start();

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
