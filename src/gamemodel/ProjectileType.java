package gamemodel;

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
}
