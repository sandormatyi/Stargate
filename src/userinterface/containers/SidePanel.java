package userinterface.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.PlayerType;
import userinterface.UIUtility;

public class SidePanel extends JPanel {
	/*
	 * The player type associated with the panel
	 */
	private PlayerType playerType;

	/*
	 * The static image to display in the background
	 */
	private Image backgroundImage;

	/*
	 * The score to display
	 */
	private int score = 0;

	public SidePanel(PlayerType playerType) {
		this.playerType = playerType;

		backgroundImage = new ImageIcon("images/" + playerType.name().toLowerCase() + "_static.png").getImage();
	}

	/*
	 * Increments the score
	 */
	public void incrementScore() {
		score++;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);

		Font savedFont = g.getFont();
		Color savedColor = g.getColor();

		g.setColor(Color.black);
		g.setFont(UIUtility.getMenuButtonFont());

		g.drawString(playerType.toString() + ":", 10, 30);
		g.drawString("" + score, 280, 30);

		g.setColor(savedColor);
		g.setFont(savedFont);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(320, 480);
	}
}
