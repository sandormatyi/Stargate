package gamemodel;

/*
 * A structure that represents the position of a MapElement
 */
public class Coord {
	public int x;
	public int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		// return String.format("[%d;%d]", x, y);
		char firstCoord = (char) ('A' + y);
		return String.format("%c%d", firstCoord, x + 1);
	}
}