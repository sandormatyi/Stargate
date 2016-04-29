package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import controller.events.ControllerEventSource;
import controller.events.IMovableEventListener;
import debug.UILogger;
import gamemodel.Box;
import gamemodel.MapElement;
import gamemodel.Movable;
import userinterface.UIUtility;
import userinterface.elements.RotatableElement;
import userinterface.elements.StackableElement;
import userinterface.elements.UIElement;

public class MovableView implements IMovableEventListener {
	/*
	 * The container of the GameObjectView (the GamePanel)
	 */
	Container parent;

	/*
	 * The set of the UIElements belonging to movables for the current game
	 */
	private Map<Movable, UIElement> movables = new HashMap<Movable, UIElement>();

	/*
	 * The set of the UIElements belonging to the boxes for the current game
	 */
	private Map<MapElement, StackableElement> boxes = new HashMap<MapElement, StackableElement>();

	public MovableView(Container parent) {
		this.parent = parent;

		ControllerEventSource.subscribe(this);
	}

	/*
	 * Draws the movables using the Graphics object given as a parameter
	 */
	public void drawMovables(Graphics g) {
		for (UIElement box : boxes.values())
			box.draw(g);

		for (UIElement movable : movables.values())
			movable.draw(g);
	}

	/*
	 * Creates a new UIElement and stores it in the set
	 */
	@Override
	public void onMovableChanged(Movable movable) {
		// Create the new UIElement that will represent the changed game object
		UIElement uiElement = new RotatableElement(movable.getPosition().getCoord(),
				UIUtility.getImage(movable.getImagePath()), UIUtility.getRotationAngle(movable.getDirection()));

		// Store the element
		movables.put(movable, uiElement);

		UILogger.log(movable + " changed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * Removes the UIElement from the set
	 */
	@Override
	public void onMovableDestroyed(Movable movable) {
		movables.remove(movable);

		UILogger.log(movable + " removed");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * If there was no box on the MapElement, creates a new StackableElement and
	 * store it in the set. Otherwise, just increases the number of stacks of
	 * the existing element.
	 */
	@Override
	public void onBoxPutDown(Box box, MapElement mapElement) {
		StackableElement uiElement = boxes.get(mapElement);

		// Check if there was a UIElement stored for the MapElement
		if (uiElement == null) {
			boxes.put(mapElement, new StackableElement(mapElement.getCoord(), UIUtility.getImage(box)));
		} else {
			uiElement.incrementStacks();
		}

		UILogger.log(box + " put down");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}

	/*
	 * Decrements the number of stacks of the StackableElement that was stored
	 * on the given MapElement. If the number of stacks reach 0, removes the
	 * element from the set.
	 */
	@Override
	public void onBoxPickedUp(Box box, MapElement mapElement) {
		StackableElement uiElement = boxes.get(mapElement);

		if (uiElement == null)
			throw new IllegalStateException("Nagy para van!"); // TODO

		uiElement.decrementStacks();

		if (uiElement.getStacks() == 0) {
			boxes.remove(mapElement);
		}

		UILogger.log(box + " picked up");

		// Notify the GamePanel that its contents need to be repainted
		parent.repaint();
		UILogger.log("GamePanel.repaint()");
	}
}
