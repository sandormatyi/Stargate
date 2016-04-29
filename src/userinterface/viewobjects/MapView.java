package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import controller.events.ControllerEventSource;
import controller.events.IMapEventListener;
import debug.UILogger;
import gamemodel.Direction;
import gamemodel.Door;
import gamemodel.MapElement;
import gamemodel.ProjectileType;
import userinterface.UIUtility;
import userinterface.elements.RotatableElement;
import userinterface.elements.UIElement;

public class MapView implements IMapEventListener {
	/*
	 * The container of the MapView (the GamePanel)
	 */
	Container parent;

	/*
	 * The set of the UIElements for the current map
	 */
	private Map<MapElement, UIElement> fields = new HashMap<MapElement, UIElement>();

	/*
	 * The set of the UIElements for the stargates
	 */
	private Map<ProjectileType, UIElement> stargates = new HashMap<ProjectileType, UIElement>();

	public MapView(Container parent) {
		this.parent = parent;

		ControllerEventSource.subscribe(this);
	}

	/*
	 * Draws the map using the Graphics object given as a parameter
	 */
	public void drawMap(Graphics g) {
		for (UIElement field : fields.values())
			field.draw(g);

		for (UIElement stargate : stargates.values())
			stargate.draw(g);
	}

	/*
	 * Creates a new UIElement and stores it in the set
	 */
	@Override
	public void onMapElementCreated(MapElement mapElement) {
		fields.put(mapElement, new UIElement(mapElement.getCoord(), UIUtility.getImage(mapElement.getImagePath())));

		UILogger.log(mapElement + " changed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * Removes the UIElement from the set
	 */
	@Override
	public void onMapElementRemoved(MapElement mapElement) {
		fields.remove(mapElement);

		UILogger.log(mapElement + " removed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * Creates a new RotatableElement for the stargate and stores it in the set
	 */
	@Override
	public void onStargateOpened(MapElement mapElement, ProjectileType type, Direction direction) {
		stargates.put(type, new RotatableElement(mapElement.getCoord(),
				UIUtility.getImage("stargate_" + type.name().toLowerCase()), UIUtility.getRotationAngle(direction)));

		UILogger.log(type + " changed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * Updates the UIElement that belongs to the door
	 */
	@Override
	public void onDoorStateChanged(Door door) {
		fields.put(door, new UIElement(door.getCoord(), UIUtility.getImage(door.getImagePath())));

		UILogger.log(door + " changed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}
}
