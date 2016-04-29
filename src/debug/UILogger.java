package debug;

import java.io.PrintStream;

public class UILogger {
	public static boolean isEnabled = false;

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
		if (isEnabled)
			outStream.println("--- " + Thread.currentThread().getName() + ": " + output);
	}
}
