package test;

public enum TestType {
	PlayerMoveRoad, PlayerMoveScale, PlayerMoveWall, PlayerMoveWormhole, PlayerMoveGap, 
	BoxPickUpRoad, BoxPickUpScale, BoxPickUpWormhole, 
	BoxPutDownRoad, BoxPutDownScale, BoxPutDownGap, BoxPutDownWall, BoxPutDownWormhole, 
	ShootWall, ShootSpecialWall, 
	ZPM;

	/*
	 * Returns the relative path of the map file belonging to the test
	 */
	public String getMapFilePath() {
		return "maps/" + name() + ".txt";
	}

	/*
	 * Returns the relative path of the input file belonging to the test
	 */
	public String getInputFilePath() {
		return "testinputs/" + name() + ".txt";
	}
}
