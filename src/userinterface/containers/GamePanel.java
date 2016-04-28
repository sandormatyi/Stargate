package userinterface.containers;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import debug.UILogger;
import userinterface.viewobjects.GameStateView;
import userinterface.viewobjects.MapView;
import userinterface.viewobjects.MovableView;

public class GamePanel extends JPanel {
	/*
	 * The component that draws the map
	 */
	private MapView mapView;

	/*
	 * The component that draws the moving object
	 */
	private MovableView movableView;

	/*
	 * The component that notifies of game events
	 */
	private GameStateView gameStateView;

	public void initialize() {
		mapView = new MapView(this);
		movableView = new MovableView(this);
		gameStateView = new GameStateView(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.clearRect(0, 0, getWidth(), getHeight());

		UILogger.log("GamePanel.paintComponent()");

		mapView.drawMap(g);
		gameStateView.drawZPMs(g);
		movableView.drawMovables(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(480, 480);
	}
}
