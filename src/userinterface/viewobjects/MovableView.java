package userinterface.viewobjects;

import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import controller.events.ControllerEventSource;
import controller.events.IMovableEventListener;
import debug.UILogger;
import gamemodel.Movable;
import userinterface.UIUtility;
import userinterface.elements.UIElement;

public class MovableView implements IMovableEventListener {
	/*
	 * The container of the MapView
	 */
	Container parent;

	/*
	 * The set of the UIMapElements for the current game
	 */
	private Map<Movable, UIElement> movables = new HashMap<Movable, UIElement>();

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

		for (UIElement movable : movables.values())
			movable.draw(g);
	}

	/*
	 * Creates a new UIMapElement and stores it in the set
	 */
	@Override
	public void onMovableChanged(Movable movable) {
		if (movable == null)
			return;

		if (movable.getPosition() == null) {
			movables.remove(movable);
			return;
		}

		movables.put(movable, new UIElement(movable.getPosition().getCoord(), UIUtility.getImage(movable)));

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
	 * Removes the UIMapElement from the set
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
}
