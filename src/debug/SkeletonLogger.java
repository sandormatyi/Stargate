package debug;

public class SkeletonLogger {
	
	/*
	 * The current log depth
	 */
	private static int logDepth = 0;
	
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
		}
	}
	
	/*
	 * Prints the textual representation of a function call to the standard output
	 */
	public static void callFunction(Object caller, String functionName) {
		System.out.println(caller.getClass().getSimpleName() + "." + functionName);
		
		incrementLogDepth();
	}
	
	/*
	 * Prints the textual representation of returning from a function to the standard output
	 */
	public static void returnFromFunction(Object returnValue) {
		if (returnValue == null) {
			System.out.println("return");
		} else {
			System.out.println("return " + returnValue.getClass().getSimpleName());
		}

		decrementLogDepth();
	}
}
