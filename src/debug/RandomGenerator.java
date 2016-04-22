package debug;

import java.util.Random;

public class RandomGenerator {

	/*
	 * A flag that determines the randomness of the generated numbers
	 */
	private static boolean isRandom = false;

	/*
	 * A random number generator
	 */
	private static Random random = new Random();

	/*
	 * Enable the randomness
	 */
	public static void enableRandom() {
		isRandom = true;
	}

	/*
	 * Disable the randomness
	 */
	public static void disableRandom() {
		isRandom = false;
	}

	/*
	 * Returns a random number that is less than the given bound if randomness
	 * is allowed. Otherwise, it just returns 0
	 */
	public static int getRandomNumber(int bound) {
		if (!isRandom)
			return 0;

		return random.nextInt(bound);
	}
}
