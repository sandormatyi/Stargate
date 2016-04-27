package userinterface;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import controller.events.ControllerEventSource;
import controller.events.IMapEventListener;
import gamemodel.Coord;
import gamemodel.MapElement;
import userinterface.containers.Application;

public class MapView implements IMapEventListener {
	/*
	 * The visual representation of a MapElement
	 */
	private class UIMapElement {
		/*
		 * The on-screen position of the UIMapElement
		 */
		private Coord position;

		/*
		 * The image associated with the UIMapElement
		 */
		private Image image;

		/*
		 * Constructor
		 */
		public UIMapElement(Coord position, String imagePath) {
			this.position = position;

			URL imageURL = getClass().getClassLoader().getResource(imagePath);
			if (imageURL != null)
				this.image = new ImageIcon(imagePath).getImage();
		}

		/*
		 * Draws the image associated with the UIMapElement with the given
		 * Graphics object
		 */
		public void draw(Graphics g) {
			g.drawImage(image, position.x * Application.getScale(), position.y * Application.getScale(), null);
		}
	}

	/*
	 * The container of the MapView
	 */
	Container parent;

	/*
	 * The set of the UIMapElements for the current game
	 */
	private Set<UIMapElement> fields = new HashSet<UIMapElement>();

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

		for (UIMapElement field : fields) {
			field.draw(g);
		}
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onMapElementCreated(MapElement mapElement) {
		fields.add(new UIMapElement(mapElement.getCoord(), mapElement.getImagePath()));

		parent.invalidate();
	}
}
