package userinterface.elements;

import java.awt.Graphics;
import java.awt.Image;

import gamemodel.Coord;
import userinterface.UIUtility;

/*
 * The visual representation of a model object
 */
public class UIElement {
	/*
	 * The image associated with the game model object
	 */
	protected Image image;

	/*
	 * The on-screen position of the game model object
	 */
	private Coord position;

	/*
	 * Constructor
	 */
	public UIElement(Coord position, Image image) {
		this.position = position;
		this.image = image;
	}

	/*
	 * Draws the image associated with the UIMapElement with the given Graphics
	 * object
	 */
	public void draw(Graphics g) {
		g.drawImage(image, position.x * UIUtility.getScale(), position.y * UIUtility.getScale(), null);
	}
}