package debug;

public class ProtoLogger {

	/*
	 * The current log depth
	 */
	@Deprecated
	private static int logDepth = 0;

	/*
	 * The logger only prints to the output if this field is true
	 */
	private static boolean printEnabled = false;

	/*
	 * Increments the log depth
	 */
	@Deprecated
	public static void incrementLogDepth() {
		logDepth++;
	}

	/*
	 * Decrements the log depth
	 */
	@Deprecated
	public static void decrementLogDepth() {
		logDepth--;

		if (logDepth < 0) {
			logDepth = 0;
		}
	}

	/*
	 * Resets the log depth
	 */
	@Deprecated
	public static void resetLogDepth() {
		logDepth = 0;
	}

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
	 * Prints the given text to the standard output
	 */
	public static void log(String output) {
		if (!printEnabled)
			return;

		System.out.println("\t" + output);
	}

	/*
	 * Prints the given text to the standard output (without indentation)
	 */
	public static void logCommand(String output) {
		if (!printEnabled)
			return;

		System.out.println(output);
	}

	/*
	 * Prints the given error to the standard error output
	 */
	public static void logError(String error) {
		System.out.println("!!! " + error + " !!!");
	}
}
