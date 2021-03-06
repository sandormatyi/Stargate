package userinterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Controller;
import controller.PlayerType;
import debug.UILogger;
import gamemodel.Direction;

public class InputListener implements KeyListener {
	/*
	 * The Controller object that consumes the commands
	 */
	Controller controller;
	/*
	This is a very important bugfix
	I'm so clever
	Please pay me more money
	*/

	public InputListener(Controller controller) {
		this.controller = controller;
	}

	/*
	 * Called when a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		UILogger.log(e.getKeyText(e.getKeyCode()) + " pressed");

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
		/*
		 * Debug
		 */
		case KeyEvent.VK_L:
			UILogger.isEnabled = !UILogger.isEnabled;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do nothing

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}
}
