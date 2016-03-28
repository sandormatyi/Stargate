package debug;

public class SkeletonLogger {

	/*
	 * The current log depth
	 */
	private static int logDepth = 0;

	/*
	 * The logger only prints to the output if this field is true
	 */
	private static boolean printEnabled = false;

	/*
	 * Increments the log depth
	 */
	private static void incrementLogDepth() {
		logDepth++;
	}

	/*
	 * Decrements the log depth
	 */
	private static void decrementLogDepth() {
		logDepth--;

		if (logDepth < 0) {
			System.err.println(SkeletonLogger.class.getSimpleName() + ": logDepth cannot be negative!");
			logDepth = 0;
		}
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
	 * Prints the textual representation of a function call to the standard
	 * output
	 */
	public static void callFunction(Object callee) {
		if (!printEnabled)
			return;

		for (int i = 0; i < logDepth; i++)
			System.out.print("\t");

		System.out.print(callee.getClass().getSimpleName() + ".");

		incrementLogDepth();
	}

	/*
	 * Prints the textual representation of entering a function to the standard
	 * output
	 */
	public static void functionCalled(String functionName, Object[] params) {
		if (!printEnabled)
			return;

		System.out.print(functionName + "(");

		if (params != null) {
			boolean firstParam = true;

			for (Object param : params) {
				if (!firstParam) {
					System.out.print(", ");
				} else {
					firstParam = false;
				}

				System.out.print(param == null ? "null" : param.getClass().getSimpleName());
			}
		}

		System.out.println(")");
	}

	/*
	 * Prints the textual representation of returning from a function to the
	 * standard output
	 */
	public static void returnFromFunction(Object returnValue) {
		if (!printEnabled)
			return;

		for (int i = 0; i < logDepth; i++)
			System.out.print("\t");

		System.out.println("return" + ((returnValue == null) ? "" : " " + returnValue.getClass().getSimpleName()));

		decrementLogDepth();
	}
}
