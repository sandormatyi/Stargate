package userinterface.elements;

import java.awt.Graphics;
import java.awt.Image;

import gamemodel.Coord;
import userinterface.UIUtility;

public class StackableElement extends UIElement {
	/*
	 * The number of stacks
	 */
	private int numberOfStacks;

	public StackableElement(Coord position, Image image, int numberOfStacks) {
		super(position, image);

		this.numberOfStacks = numberOfStacks;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);

		g.drawString("" + numberOfStacks, position.x * UIUtility.getScale(), position.y * UIUtility.getScale());
	}
}
