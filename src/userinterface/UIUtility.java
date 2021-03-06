package userinterface;

import java.awt.Font;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.InvalidParameterException;

import javax.swing.ImageIcon;

import debug.UILogger;
import gamemodel.Direction;

public class UIUtility {
	/*
	 * The main scale of the game
	 */
	private static final int scale = 32;

	/*
	 * Returns the image that represents the object on the user interface
	 */
	public static Image getImage(Object gameObject) {
		String relPath = gameObject.getClass().getSimpleName().toLowerCase();
		
		return getImage(relPath);
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

		String resPath = resURL.getPath();

		try {
			resPath = URLDecoder.decode(resURL.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Do nothing
		}

		return new ImageIcon(resPath).getImage();
	}

	/*
	 * Returns the scale of the game
	 */
	public static int getScale() {
		return scale;
	}

	/*
	 * Returns the delay between two steps of the projectile (in ms)
	 */
	public static int getProjectileDelay() {
		return 100;
	}

	/*
	 * Returns the delay between two steps of the replicator (in ms)
	 */
	public static int getReplicatorDelay() {
		return 500;
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

	/*
	 * Returns the font used to display the number of boxes
	 */
	public static Font getBoxCountFont() {
		return new Font("Consolas", Font.BOLD, 16);
	}

	/*
	 * Returns the font used in the main menu
	 */
	public static Font getMenuButtonFont() {
		return new Font("Arial", Font.BOLD, scale);
	}
}
