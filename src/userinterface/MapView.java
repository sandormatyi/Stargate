package userinterface;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import controller.events.ControllerEventSource;
import controller.events.IMapEventListener;
import gamemodel.MapElement;
import userinterface.elements.UIElement;

public class MapView implements IMapEventListener {
	/*
	 * The container of the MapView
	 */
	Container parent;

	/*
	 * The set of the UIMapElements for the current game
	 */
	private Set<UIElement> fields = new HashSet<UIElement>();

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

		for (UIElement field : fields)
			field.draw(g);
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onMapElementCreated(MapElement mapElement) {
		fields.add(new UIElement(mapElement.getCoord(), mapElement.getImagePath()));

		parent.invalidate();
	}
}
