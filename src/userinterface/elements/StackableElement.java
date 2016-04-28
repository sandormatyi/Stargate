package userinterface.elements;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import gamemodel.Coord;
import userinterface.UIUtility;

public class StackableElement extends UIElement {
	/*
	 * The number of stacks
	 */
	private int numberOfStacks;

	public StackableElement(Coord position, Image image) {
		super(position, image);

		this.numberOfStacks = 1;
	}

	/*
	 * Returns the number of stacks
	 */
	public int getStacks() {
		return numberOfStacks;
	}

	/*
	 * Increments the number of stacks
	 */
	public void incrementStacks() {
		numberOfStacks++;
	}

	/*
	 * Decrements the number of stacks
	 */
	public void decrementStacks() {
		numberOfStacks--;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);

		Font savedFont = g.getFont();

		int center_x = Math.round(((float) position.x + 0.33f) * UIUtility.getScale());
		int center_y = Math.round(((float) position.y + 0.66f) * UIUtility.getScale());

		g.setFont(UIUtility.getBoxCountFont());
		g.drawString("" + numberOfStacks, center_x, center_y);

		g.setFont(savedFont);
	}
}
