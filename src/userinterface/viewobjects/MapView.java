package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import controller.events.ControllerEventSource;
import controller.events.IMapEventListener;
import debug.UILogger;
import gamemodel.MapElement;
import userinterface.UIUtility;
import userinterface.elements.UIElement;

public class MapView implements IMapEventListener {
	/*
	 * The container of the MapView
	 */
	Container parent;

	/*
	 * The set of the UIElements for the current game
	 */
	private Map<MapElement, UIElement> fields = new HashMap<MapElement, UIElement>();

	public MapView(Container parent) {
		this.parent = parent;

		ControllerEventSource.subscribe(this);
	}

	/*
	 * Draws the map using the Graphics object given as a parameter
	 */
	public void drawMap(Graphics g) {
		if (g == null)
			return;

		for (UIElement field : fields.values())
			field.draw(g);
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onMapElementCreated(MapElement mapElement) {
		fields.put(mapElement, new UIElement(mapElement.getCoord(), UIUtility.getImage(mapElement)));

		UILogger.log(mapElement + " changed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}

	/*
	 * Removes the MapElement from the set
	 */
	@Override
	public void onMapElementRemoved(MapElement mapElement) {
		fields.remove(mapElement);

		UILogger.log(mapElement + " removed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}
}
