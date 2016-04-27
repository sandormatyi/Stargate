package userinterface.containers;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import userinterface.MapView;

public class GamePanel extends JPanel {
	/*
	 * The component that draws the map
	 */
	private MapView mapView = new MapView();

	public GamePanel() {
		setPreferredSize(new Dimension(400, 400));
	}

	@Override
	public void paintComponent(Graphics g) {
		mapView.drawMap(g);
	}
}
