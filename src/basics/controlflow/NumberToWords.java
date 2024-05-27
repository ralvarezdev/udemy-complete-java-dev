package basics.controlflow;

// Exercise 24
public class NumberToWords {
	public static void numberToWords(int number) {
		if (number < 0)
			System.out.println("Invalid Value");

		int reversedNumber = reverse(number);
		int currDigit = getDigitCount(number);
		String character;

		while (reversedNumber > 0 || currDigit > 0) {
			character = switch (reversedNumber % 10) {
			case 0 -> "Zero";
			case 1 -> "One";
			case 2 -> "Two";
			case 3 -> "Three";
			case 4 -> "Four";
			case 5 -> "Five";
			case 6 -> "Six";
			case 7 -> "Seven";
			case 8 -> "Eight";
			case 9 -> "Nine";
			default -> "Invalid Value";
			};

			reversedNumber /= 10;
			currDigit--;
			System.out.println(character);
		}
	}

	public static int reverse(int number) {
		int reversedNumber = 0;
		int stringLength = ("" + number).length();
		int numberLength = number > 0 ? stringLength : stringLength - 1;
		int power = (int) Math.pow(10.0, (double) numberLength - 1);

		for (int i = 0; i < numberLength; i++) {
			reversedNumber += number % 10 * power;
			number /= 10;
			power /= 10;
		}

		return reversedNumber;

	}

	public static int getDigitCount(int number) {
		if (number < 0)
			return -1;

		return ("" + number).length();
	}
}
