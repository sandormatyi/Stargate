package debug;

import java.io.PrintStream;

public class ProtoLogger {

	/*
	 * The logger only prints to the output if this field is true
	 */
	private static boolean printEnabled = true;

	/*
	 * The output stream to which the logger writes the messages
	 */
	private static PrintStream outStream = System.out;

	/*
	 * Enables printing to the standard output
	 */
	public static void enablePrint() {
		printEnabled = true;
	}

	/*
	 * Disables printing to the standard output
	 */
	public static void disablePrint() {
		printEnabled = false;
	}

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
		if (!printEnabled)
			return;

		outStream.println("\t" + output);
	}

	/*
	 * Prints the given text to the standard output (without indentation)
	 */
	public static void logCommand(String output) {
		if (!printEnabled)
			return;

		outStream.println(output);
	}

	/*
	 * Prints the given error to the standard error output
	 */
	public static void logError(String error) {
		outStream.println("!!! " + error + " !!!");
	}
}
