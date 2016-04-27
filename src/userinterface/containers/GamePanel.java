package userinterface.containers;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import debug.UILogger;
import userinterface.MapView;
import userinterface.MovableView;

public class GamePanel extends JPanel {
	/*
	 * The component that draws the map
	 */
	private MapView mapView;

	/*
	 * The component that draws the moving object
	 */
	private MovableView movableView;

	public void initialize() {
		mapView = new MapView(this);
		movableView = new MovableView(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.clearRect(0, 0, getWidth(), getHeight());

		UILogger.log("GamePanel.paintComponent()");

		mapView.drawMap(g);
		movableView.drawMovables(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}
}
