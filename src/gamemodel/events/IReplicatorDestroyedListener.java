package gamemodel.events;

import gamemodel.Replicator;

public interface IReplicatorDestroyedListener {
	/*
	 * Handles the destruction of a Replicator
	 */
	void onReplicatorDestroyed(Replicator replicator);
}
