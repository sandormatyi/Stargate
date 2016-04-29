package userinterface.elements;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gamemodel.Coord;

public class RotatableElement extends UIElement {

	public RotatableElement(Coord position, Image image, int rotationAngle) {
		super(position);

		// Create a buffered image with transparency
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bufferedImage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();

		// Rotate the BufferedImage
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(rotationAngle), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

		// Store the rotated image
		this.image = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(bufferedImage, null);
	}
}
