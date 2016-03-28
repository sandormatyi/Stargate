package test;

import controller.Controller;

abstract class Test {

	/*
	 * The controller used to issue commands during the test
	 */
	protected Controller controller;

	public Test(Controller controller) {
		this.controller = controller;
	}

	/*
	 * This method is run before the test starts
	 */
	public abstract void setUp();

	/*
	 * This method runs the test
	 */
	public abstract void run();
}
