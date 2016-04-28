package userinterface;

import java.awt.Image;
import java.net.URL;
import java.security.InvalidParameterException;

import javax.swing.ImageIcon;

import debug.UILogger;
import gamemodel.Direction;

public class UIUtility {

	/*
	 * Returns the image that represents the object on the user interface
	 */
	public static Image getImage(Object gameObject) {
		String relPath = gameObject.getClass().getSimpleName().toLowerCase();
		URL resURL = UIUtility.class.getClassLoader().getResource("images/gameobjects/" + relPath + ".png");

		if (resURL == null) {
			UILogger.log("File not found: images/gameobjects/" + relPath + ".png");
			return null;
		}

		return new ImageIcon(resURL).getImage();
	}

	/*
	 * Returns the image that represents the object on the user interface
	 */
	public static Image getImage(String relPath) {
		URL resURL = UIUtility.class.getClassLoader().getResource("images/gameobjects/" + relPath + ".png");

		if (resURL == null) {
			UILogger.log("File not found: images/gameobjects/" + relPath + ".png");
			return null;
		}

		return new ImageIcon(resURL).getImage();
	}

	/*
	 * Returns the scale of the game
	 */
	public static int getScale() {
		return 32;
	}

	/*
	 * Returns the delay between two steps of the projectile (in ms)
	 */
	public static int getProjectileDelay() {
		return 200;
	}

	/*
	 * Returns the delay between two steps of the replicator (in ms)
	 */
	public static int getReplicatorDelay() {
		return 500;
	}

	/*
	 * Returns the delay between two user inputs
	 */
	public static int getInputDelay() {
		return 750;
	}

	/*
	 * Gets the rotation angle specified by the direction (starting from north)
	 */
	public static int getRotationAngle(Direction dir) {
		switch (dir) {
		case NORTH:
			return 0;
		case EAST:
			return 90;
		case SOUTH:
			return 180;
		case WEST:
			return 270;
		default:
			throw new InvalidParameterException();
		}
	}
}
