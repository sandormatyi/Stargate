package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import controller.events.ControllerEventSource;
import controller.events.IZPMEventListener;
import debug.UILogger;
import gamemodel.MapElement;
import gamemodel.ZPM;
import userinterface.UIUtility;
import userinterface.elements.UIElement;

public class GameStateView implements IZPMEventListener {

	/*
	 * The container of the MapView
	 */
	Container parent;

	/*
	 * The set of the UIElements for the current game
	 */
	private Map<ZPM, UIElement> zpms = new HashMap<ZPM, UIElement>();

	public GameStateView(Container parent) {
		this.parent = parent;

		ControllerEventSource.subscribe(this);
	}

	/*
	 * Draws the ZPMs using the Graphics object given as a parameter
	 */
	public void drawZPMs(Graphics g) {
		if (g == null)
			return;

		for (UIElement zpm : zpms.values())
			zpm.draw(g);
	}

	/*
	 * Creates a new UIElement for the ZPM and puts it in the set
	 */
	@Override
	public void onZPMCreated(ZPM zpm, MapElement position) {
		zpms.put(zpm, new UIElement(position.getCoord(), UIUtility.getImage(zpm)));

		UILogger.log(zpm + " changed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}

	/*
	 * Removes the ZPM from the set and invalidates the parent view
	 */
	@Override
	public void onZPMPickedUp(ZPM zpm) {
		zpms.remove(zpm);

		UILogger.log(zpm + " removed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}
}
