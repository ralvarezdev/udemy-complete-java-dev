package basics.controlflow;

import java.util.Scanner;

public class ReadingUserInput {
	public static void main(String[] args) {
		ReadingUserInput.challenge();
	}

	public static void challenge() {
		System.out.println("Reading User Input Challenge");

		Scanner scanner = new Scanner(System.in);

		int validCounter = 0;
		double number, sum = 0;
		String input;

		while (validCounter < 5) {
			System.out.print("Enter number #" + (validCounter + 1) + ": ");
			input = scanner.nextLine();

			try {
				number = Double.parseDouble(input);
			} catch (NumberFormatException exception) {
				continue;
			}

			sum += number;
			validCounter++;
		}
		scanner.close();

		System.out.println("\nSum of Five Numbers = " + sum);
	}
}
