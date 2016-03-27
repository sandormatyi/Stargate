package gamemodel;

public enum ProjectileType {
	YELLOW, BLUE;

	static ProjectileType getOppositeType(ProjectileType type) {
		switch (type) {
		case YELLOW:
			return BLUE;
		case BLUE:
			return YELLOW;
		default:
			return null;
		}
	}
}
