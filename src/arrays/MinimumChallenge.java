package arrays;

import java.util.Scanner;

public class MinimumChallenge {
	public static void main(String[] args) {
		Integer[] numbersArray = MinimumChallenge.readIntegers();

		System.out.print("Numbers Entered: ");
		for (Integer number : numbersArray)
			System.out.print(number + " ");
		System.out.println();

		System.out.println("Minimum Number: " + MinimumChallenge.findMin(numbersArray));
	}

	public static Integer[] readIntegers() {
		System.out.print("Enter Integers Delimited with a Comma: ");
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		scanner.close();

		String[] numbersStringArray = input.split(",");

		Integer[] numbersArray = new Integer[numbersStringArray.length];
		int i = 0, number;

		for (String numberString : numbersStringArray)
			try {
				number = Integer.parseInt(numberString);
				numbersArray[i++] = number;
			} catch (NumberFormatException e) {
				continue;
			}

		return numbersArray;
	}

	public static Integer findMin(Integer[] numbersArray) {
		Integer minNumber = Integer.MAX_VALUE;

		for (Integer number : numbersArray)
			if (number < minNumber)
				minNumber = number;

		return minNumber;
	}
}
