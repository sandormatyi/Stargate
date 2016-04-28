package userinterface.containers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	/*
	 * The static image to display in the background
	 */
	Image backgroundImage;

	public SidePanel(String imagePath) {
		backgroundImage = new ImageIcon(imagePath).getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(320, 480);
	}
}
