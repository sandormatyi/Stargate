package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import debug.ProtoLogger;

public class PrototypeValidator {
	/*
	 * Dummy output stream that checks if the printed output is equal to the
	 * expected result of the given test
	 */
	class TestOutStream extends PrintStream {
		/*
		 * The BufferedReader that the expected results can be read from
		 */
		private BufferedReader input;

		/*
		 * The type of the current test
		 */
		private TestType test;

		/*
		 * The number of the incorrect lines during the validation of the test
		 */
		private int errorNumber = 0;

		public TestOutStream(OutputStream out, TestType test)
				throws FileNotFoundException, UnsupportedEncodingException {
			super(out, true, "UTF-8");

			this.test = test;

			// Read the file that contains the expected results for the test
			String resultFilePath = test.getExpectedResultFilePath();
			URL resURL = PrototypeValidator.class.getClassLoader().getResource(resultFilePath);

			if (resURL == null)
				throw new FileNotFoundException("A megadott fájl nem található: " + resultFilePath);

			input = new BufferedReader(new InputStreamReader(new FileInputStream(resURL.getPath()), "UTF-8"));
		}

		/*
		 * Checks the next line of the output to be printed
		 */
		@Override
		public void println(String x) {
			try {
				String expectedOutput = input.readLine();

				if (!expectedOutput.equals(x)) {
					errorNumber++;

					super.println("Helytelen kimenet a " + test.name() + " teszt futtatása során!\n\tElvárt: "
							+ expectedOutput + "\n\tKapott: " + x);
				}
			} catch (IOException e) {
				// Ignore exception
			}
		}

		/*
		 * Returns the number of the incorrect lines
		 */
		public int getIncorrectLines() {
			return errorNumber;
		}
	}

	/*
	 * Runs all tests and compares their results with the expected results from
	 * the 'res/expectedresults' folder
	 */
	public void runAllTestsAndCompareResults() {
		PrintStream originalOutStream = System.out;

		int totalErrors = 0;

		try {
			for (TestType test : TestType.values()) {
				System.out.println("-------------------------------------------------------------");
				System.out.println(test.name() + " teszt:");
				System.out.println("-------------------------------------------------------------");

				TestOutStream testStream = new TestOutStream(originalOutStream, test);
				ProtoLogger.redirectOutput(testStream);

				TestRunner.runTest(test);

				int errors = testStream.getIncorrectLines();

				System.out.println("Hibás sorok száma: " + errors + "\n");

				totalErrors += errors;
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			// Reset the output stream to the original one
			ProtoLogger.redirectOutput(originalOutStream);

			System.out.println("A tesztek ellenőrzése befejeződött, hibás sorok száma: " + totalErrors);
		}
	}
}
