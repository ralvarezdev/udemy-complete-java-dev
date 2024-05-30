package lambdas;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MiniChallenge2To5 {
	final static String SEP = "-".repeat(20);

	public static void main(String[] args) {
		LinkedList<String> tests = new LinkedList<>();

		// Test 1 (Mini Challenge 3)
		tests.add("1234567890");

		// Test 2
		tests.add("""
				Lorem ipsum dolor sit amet, consectetur adipiscing elit,
				sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
				Ut ornare lectus sit amet est. Non tellus orci ac auctor augue mauris augue.
				Pellentesque sit amet porttitor eget dolor. Adipiscing elit ut aliquam purus.
				""");

		// Mini Challenge 2
		UnaryOperator<String> everySecondCharLambda = source -> {
			StringBuilder returnVal = new StringBuilder();

			for (int i = 0; i < source.length(); i++)
				if (i % 2 == 1)
					returnVal.append(source.charAt(i));

			return returnVal.toString();
		};

		tests.forEach(test -> {
			System.out.println("Test:\n");

			// Mini Challenge 3
			System.out.println("Every Second Char Method:");
			System.out.println(everySecondChar(test));
			System.out.println(SEP);

			System.out.println("Every Second Char Lambda:");
			System.out.println(everySecondCharLambda.apply(test));
			System.out.println(SEP);

			// Mini Challenge 5
			System.out.println("Every Second Char Functional Method:");
			System.out.println(everySecondChar(everySecondCharLambda, test));
			System.out.println();
		});

	}

	public static String everySecondChar(String source) {
		StringBuilder returnVal = new StringBuilder();

		for (int i = 0; i < source.length(); i++)
			if (i % 2 == 1)
				returnVal.append(source.charAt(i));

		return returnVal.toString();
	}

	// Mini Challenge 4
	public static String everySecondChar(Function<String, String> func, String input) {
		return func.apply(input);
	}
}
