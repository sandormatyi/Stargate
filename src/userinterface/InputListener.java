package userinterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Controller;
import controller.PlayerType;
import gamemodel.Direction;

public class InputListener implements KeyListener {
	/*
	 * The Controller object that consumes the commands
	 */
	Controller controller;

	public InputListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.NORTH);
			break;
		case KeyEvent.VK_A:
			controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.WEST);
			break;
		case KeyEvent.VK_S:
			controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.SOUTH);
			break;
		case KeyEvent.VK_D:
			controller.moveOrTurnPlayer(PlayerType.ONeill, Direction.EAST);
			break;
		case KeyEvent.VK_SPACE:
			controller.pickUpOrPutDownBox(PlayerType.ONeill);
			break;
		case KeyEvent.VK_F:
			controller.shootFirst(PlayerType.ONeill);
			break;
		case KeyEvent.VK_G:
			controller.shootSecond(PlayerType.ONeill);
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
