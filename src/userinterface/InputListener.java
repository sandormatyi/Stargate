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
		/*
		 * O'Neill's commands
		 */
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
		/*
		 * Jaffa's commands
		 */
		case KeyEvent.VK_UP:
			controller.moveOrTurnPlayer(PlayerType.Jaffa, Direction.NORTH);
			break;
		case KeyEvent.VK_LEFT:
			controller.moveOrTurnPlayer(PlayerType.Jaffa, Direction.WEST);
			break;
		case KeyEvent.VK_DOWN:
			controller.moveOrTurnPlayer(PlayerType.Jaffa, Direction.SOUTH);
			break;
		case KeyEvent.VK_RIGHT:
			controller.moveOrTurnPlayer(PlayerType.Jaffa, Direction.EAST);
			break;
		case KeyEvent.VK_NUMPAD3:
			controller.pickUpOrPutDownBox(PlayerType.Jaffa);
			break;
		case KeyEvent.VK_NUMPAD1:
			controller.shootFirst(PlayerType.Jaffa);
			break;
		case KeyEvent.VK_NUMPAD2:
			controller.shootSecond(PlayerType.Jaffa);
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
