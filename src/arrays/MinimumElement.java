package arrays;

import java.util.Scanner;

public class MinimumElement {
	@SuppressWarnings("unused")
	private static int readInteger() {
		System.out.print("Enter a Number: ");

		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		scanner.close();

		return number;
	}

	@SuppressWarnings("unused")
	private static int[] readElements(int elements) {
		Scanner scanner = new Scanner(System.in);
		int[] numbers = new int[elements];
		int i = 0;

		while (i < elements) {
			System.out.print("Enter a Number: ");
			numbers[i++] = scanner.nextInt();
		}
		scanner.close();

		return numbers;
	}

	@SuppressWarnings("unused")
	private static int findMin(int[] numbers) {
		int minNumber = Integer.MAX_VALUE;

		for (int number : numbers)
			if (number < minNumber)
				minNumber = number;

		return minNumber;
	}
}