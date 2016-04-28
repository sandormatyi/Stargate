package controller;

public enum PlayerType {
	ONeill("Colonel O'Neill"), Jaffa("The Jaffa");

	/*
	 * The user-friendly name of the player
	 */
	String displayName;

	PlayerType(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
