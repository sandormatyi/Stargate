package gamemodel;

import java.awt.Color;

public enum ProjectileType {
	YELLOW, BLUE, RED, GREEN;

	static ProjectileType getOppositeType(ProjectileType type) {
		switch (type) {
		case YELLOW:
			return BLUE;
		case BLUE:
			return YELLOW;
		case GREEN:
			return RED;
		case RED:
			return GREEN;
		default:
			return null;
		}
	}

	static Color getColor(ProjectileType type) {
		switch (type) {
		case YELLOW:
			return Color.yellow;
		case BLUE:
			return Color.blue;
		case GREEN:
			return Color.green;
		case RED:
			return Color.red;
		default:
			return null;
		}
	}
}
