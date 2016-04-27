package userinterface.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import gamemodel.Coord;
import userinterface.containers.Application;

/*
 * The visual representation of a model object
 */
public class UIElement {
	/*
	 * The image associated with the game model object
	 */
	private Image image;

	/*
	 * The on-screen position of the game model object
	 */
	private Coord position;

	/*
	 * Constructor
	 */
	public UIElement(Coord position, String imagePath) {
		this.position = position;

		URL imageURL = getClass().getClassLoader().getResource(imagePath);
		if (imageURL != null)
			this.image = new ImageIcon(imagePath).getImage();
	}

	/*
	 * Draws the image associated with the UIMapElement with the given Graphics
	 * object
	 */
	public void draw(Graphics g) {
		g.drawImage(image, position.x * Application.getScale(), position.y * Application.getScale(), null);
	}
}