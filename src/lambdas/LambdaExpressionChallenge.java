package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdaExpressionChallenge {
	private static Random random = new Random();

	public static void main(String[] args) {
		String[] names = { "Bob", "Anna", "Ramon", "Jesus", "Ivana", "David", "Javier" };

		Arrays.setAll(names, i -> {
			final String firstName = names[i].toUpperCase();
			final String middleName = getRandomChar('A', 'Z') + ".";
			final String lastName = getReversedString(firstName);

			return String.join(" ", firstName, middleName, lastName);
		});

		System.out.println("Printing Names:");
		printList(Arrays.asList(names));

		// Remove Names where the First Name is a Palindrome
		ArrayList<String> namesList = new ArrayList<>(Arrays.asList(names));
		namesList.removeIf(name -> {
			String[] nameParts = name.split(" ");

			// Check if the First Name is a Palindrome
			return nameParts[0].equals(nameParts[2]);
		});

		System.out.println("Printing Names after Deleting Palindromes:");
		printList(namesList);
	}

	public static <T> void printList(List<T> elements) {
		elements.forEach(element -> System.out.println(element));
		System.out.println();
	}

	public static char getRandomChar(char startChar, char endChar) {
		return (char) random.nextInt((int) startChar, (int) endChar + 1);
	}

	public static String getReversedString(String temp) {
		return new StringBuilder(temp).reverse().toString();
	}

	public static String getReversedStringAppended(String temp) {
		return temp + " " + getReversedString(temp);
	}
}
