package debug;

import java.io.PrintStream;

public class UILogger {

	/*
	 * The output stream to which the logger writes the messages
	 */
	private static PrintStream outStream = System.out;

	/*
	 * Redirects the output of the logger to the given PrintStream
	 */
	public static void redirectOutput(PrintStream printStream) {
		outStream = printStream;
	}

	/*
	 * Prints the given text to the standard output
	 */
	public static void log(String output) {
		// outStream.println("--- " + Thread.currentThread().getName() + " --- "
		// + output);
	}
}
