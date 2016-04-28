package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

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
	 * The container of the GameObjectView
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
		if (g == null)
			return;

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
		UIElement uiElement = new RotatableElement(movable.getPosition().getCoord(),
				UIUtility.getImage(movable.getImagePath()), UIUtility.getRotationAngle(movable.getDirection()));

		movables.put(movable, uiElement);

		UILogger.log(movable + " changed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}

	/*
	 * Removes the UIElement from the set
	 */
	@Override
	public void onMovableDestroyed(Movable movable) {
		movables.remove(movable);

		UILogger.log(movable + " removed");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onBoxPutDown(Box box, MapElement mapElement) {
		StackableElement uiElement = boxes.get(mapElement);

		if (uiElement == null) {
			boxes.put(mapElement, new StackableElement(mapElement.getCoord(), UIUtility.getImage(box)));
		} else {
			uiElement.incrementStacks();
		}

		UILogger.log(box + " put down");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onBoxPickedUp(Box box, MapElement mapElement) {
		StackableElement uiElement = boxes.get(mapElement);

		if (uiElement == null)
			throw new IllegalStateException("Nagy para van!");

		uiElement.decrementStacks();

		if (uiElement.getStacks() == 0) {
			boxes.remove(mapElement);
		}

		UILogger.log(box + " picked up");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.repaint();
				UILogger.log("GamePanel.repaint()");
			}
		});
	}
}
