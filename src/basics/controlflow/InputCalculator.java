package basics.controlflow;

import java.util.Scanner;

// Exercise 28
public class InputCalculator {
	public static void inputThenPrintSumAndAverage() {
		Scanner scanner = new Scanner(System.in);
		int counter = 0;
		int sum = 0, number;
		String input;

		while (true) {
			input = scanner.nextLine();

			try {
				number = Integer.parseInt(input);
			} catch (NumberFormatException exception) {
				break;
			}

			sum += number;
			counter++;
		}
		scanner.close();

		long avg = Math.round((double) sum / counter);

		System.out.println("SUM = " + sum + " AVG = " + avg);
	}

}
