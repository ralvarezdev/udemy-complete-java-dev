package basics;

import java.util.Scanner;

public class ControlFlow {
	public static void main(String[] args) {
		traditionalSwitchChallenge();
		enhancedSwitchChallenge();
		forMiniChallenge();
		forChallenge();
		sum3And5Challenge();
		whileChallenge();
		digitSumChallenge();
		readingUserInputChallenge();
		minMaxChallenge();
	}

	public static void traditionalSwitchChallenge() {
		String word = "ralvarezdev";

		System.out.println("Traditional Switch Challenge");

		for (int i = 0; i < word.length(); i++)
			System.out.print(nato(word.charAt(i)) + " ");
	}

	private static String nato(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a':
			return "Able";
		case 'b':
			return "Baker";
		case 'c':
			return "Charlie";
		case 'd':
			return "Dog";
		case 'e':
			return "Easy";
		case 'f':
			return "Fox";
		case 'g':
			return "George";
		case 'h':
			return "How";
		case 'i':
			return "Item";
		case 'j':
			return "Jig";
		case 'k':
			return "King";
		case 'l':
			return "Love";
		case 'm':
			return "Mike";
		case 'n':
			return "Nan";
		case 'o':
			return "Oboe";
		case 'p':
			return "Peter";
		case 'q':
			return "Queen";
		case 'r':
			return "Roger";
		case 's':
			return "Sugar";
		case 't':
			return "Tare";
		case 'u':
			return "Uncle";
		case 'v':
			return "Victor";
		case 'w':
			return "William";
		case 'x':
			return "X-ray";
		case 'y':
			return "Yoke";
		case 'z':
			return "Zebra";
		default:
			return "Not Found";
		}
	}

	public static void enhancedSwitchChallenge() {
		System.out.println("\n\nEnhanced Switch Challenge");

		for (int i = 0; i <= 7; i++)
			System.out.println("Day " + i + " = " + dayOfTheWeek(i));
	}

	private static String dayOfTheWeek(int day) {
		return switch (day) {
		case 0 -> "Sunday";
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thursday";
		case 5 -> "Friday";
		case 6 -> "Saturday";
		default -> "Invalid Day";
		};
	}

	// Exercise 13
	public static void printNumberInWord(int number) {
		String numberStr = switch (number) {
		case 0 -> "ZERO";
		case 1 -> "ONE";
		case 2 -> "TWO";
		case 3 -> "THREE";
		case 4 -> "FOUR";
		case 5 -> "FIVE";
		case 6 -> "SIX";
		case 7 -> "SEVEN";
		case 8 -> "EIGHT";
		case 9 -> "NINE";
		default -> "OTHER";
		};

		System.out.print(numberStr);
	}

	// Exercise 14
	public static int getDaysInMonth(int month, int year) {
		if (month < 1 || month > 12 || year < 1 || year > 9999)
			return -1;

		if (Expressions.isLeapYear(year) && month == 2)
			return 29;

		return switch (month) {
		case 2 -> 28;
		case 1, 3, 5, 7, 8, 10, 12 -> 31;
		case 4, 6, 9, 11 -> 30;
		default -> -1;
		};
	}

	public static void forMiniChallenge() {
		double interest;
		final double AMOUNT = 100;

		System.out.println("\nFor Mini Challenge");

		for (double i = 7.5; i <= 10.0; i += 0.25) {
			interest = calculateInterest(AMOUNT, i);
			System.out.println("Interest (" + i + "%) for " + AMOUNT + "$ = " + interest);
		}
	}

	private static double calculateInterest(double amount, double interestRate) {
		return amount * interestRate / 100;
	}

	public static void forChallenge() {
		boolean isPrime;
		int primeCounter = 0;

		System.out.println("\nFor Challenge\nY (Prime Number)\nN (not a Prime Number)\n");

		for (int i = 0; i <= 1000; i++) {
			isPrime = isPrime(i);

			System.out.println("" + i + (isPrime ? " Y" : " N"));

			if (isPrime)
				primeCounter++;
		}

		System.out.println("\nNumber of Prime Number: " + primeCounter);
	}

	private static boolean isPrime(int wholeNumber) {
		if (wholeNumber <= 2)
			return wholeNumber == 2;

		for (int divisor = 2; divisor <= wholeNumber / 2; divisor++)
			if (wholeNumber % divisor == 0)
				return false;

		return true;
	}

	public static void sum3And5Challenge() {
		int matches = 0;
		int sumMatches = 0;

		System.out.println("\nSum 3 and 5 Challenge");

		for (int i = 1; i <= 1000 && matches < 5; i++)
			if (i % 3 == 0 && i % 5 == 0) {
				matches++;
				sumMatches += i;

				System.out.println(i);
			}

		System.out.println("Sum of Matches: " + sumMatches);
	}

	// Exercise 15
	public static boolean isOdd(int number) {
		if (number <= 0)
			return false;

		return number % 2 != 0;
	}

	public static int sumOdd(int start, int end) {
		int sumOdd = 0;

		if (start > end || start < 0 || end < 0)
			return -1;

		for (int i = start; i <= end; i++)
			if (isOdd(i))
				sumOdd += i;

		return sumOdd;
	}

	public static void whileChallenge() {
		int i = 5, start = i;
		int evenCounter = 0;

		System.out.println("\nWhile Challenge");

		while (i <= 20 && evenCounter < 5)
			if (isEvenNumber(i++)) {
				System.out.println(i - 1);
				evenCounter++;
			}

		System.out.println("Amount of Even Numbers Found: " + evenCounter);
		System.out.println("Amoun of Odd Numbers Found: " + (isEvenNumber(start) ? evenCounter - 1 : evenCounter));
	}

	private static boolean isEvenNumber(int number) {
		return number % 2 == 0;
	}

	public static void digitSumChallenge() {
		System.out.println("\nDigit Sum Challenge");

		for (int i = 0; i <= 20; i++)
			System.out.println("Sum Digits of " + i * i + " = " + sumDigits(i * i));
	}

	private static int sumDigits(int number) {
		if (number < 0)
			return -1;

		int sum = 0;

		while (number != 0) {
			sum += number % 10;
			number /= 10;
		}

		return sum;
	}

	// Exercise 16
	public static boolean isPalindrome(int number) {
		String numberAbs = "" + Math.abs(number);
		StringBuilder numberStringReverse = new StringBuilder(numberAbs).reverse();

		return numberAbs.equals(numberStringReverse.toString());
	}

	// Exercise 17
	public static int sumFirstAndLastDigit(int number) {
		if (number < 0)
			return -1;

		String numberString = "" + number;
		char first = numberString.charAt(0);
		char last = numberString.charAt(numberString.length() - 1);

		return Character.getNumericValue(first) + Character.getNumericValue(last);
	}

	// Exercise 18
	public static int getEvenDigitSum(int number) {
		if (number < 0)
			return -1;

		int sum = 0;

		while (number != 0) {
			if (number % 2 == 0)
				sum += number % 10;
			number /= 10;
		}

		return sum;
	}

	// Exercise 19
	public static boolean hasSharedDigit(int a, int b) {
		if (a < 10 || b < 10 || a > 99 || b > 99)
			return false;

		int a1 = a % 10;
		int a2 = a / 10;
		int b1 = b % 10;
		int b2 = b / 10;

		return (a1 == b1 || a1 == b2 || a2 == b1 || a2 == b2);
	}

	// Exercise 20
	public static boolean hasSameLastDigit(int a, int b, int c) {
		if (!isValid(a) || !isValid(b) || !isValid(c))
			return false;

		int aLast = a % 10;
		int bLast = b % 10;
		int cLast = c % 10;

		int equalsCounter = (aLast == bLast ? 1 : 0) + (aLast == cLast ? 1 : 0) + (bLast == cLast ? 1 : 0);

		return equalsCounter > 0;
	}

	public static boolean isValid(int a) {
		return a >= 10 && a <= 1000;
	}

	// Exercise 21
	public static void printFactors(int number) {
		if (number < 1) {
			System.out.print("Invalid Value");
			return;
		}

		for (int i = 1; i <= number / 2; i++)
			if (number % i == 0)
				System.out.printf("%d ", i);
		System.out.printf("%d ", number);
	}

	// Exercise 22
	public static int getGreatestCommonDivisor(int first, int second) {
		if (first < 10 || second < 10)
			return -1;

		int greatestDivisor = (first < second) ? first : second;

		while (greatestDivisor > 1) {
			if (first % greatestDivisor == 0 && second % greatestDivisor == 0)
				return greatestDivisor;

			greatestDivisor--;
		}

		return 1;
	}

	// Exercise 23
	public static boolean isPerfectNumber(int number) {
		if (number < 1)
			return false;

		int sum = 0;
		int divisor = number / 2;

		while (divisor > 0) {
			if (number % divisor == 0)
				sum += divisor;
			divisor--;
		}

		return sum == number;
	}

	// Exercise 24
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

	// Exercise 25
	public static boolean canPack(int bigCount, int smallCount, int goal) {
		if (bigCount < 0 || smallCount < 0 || goal < 0)
			return false;

		final int BIG_KG = 5;
		final int SMALL_KG = 1;
		int totalCount = bigCount * BIG_KG + smallCount * SMALL_KG;

		if (totalCount < goal)
			return false;

		if (totalCount == goal)
			return true;

		int goalRemainder = goal;
		int bigCountRemainder = bigCount;
		int smallCountRemainder = smallCount;

		while (goalRemainder >= BIG_KG && bigCountRemainder > 0) {
			goalRemainder -= BIG_KG;
			bigCountRemainder--;
		}

		return smallCountRemainder >= goalRemainder;
	}

	// Exercise 26
	public static int getLargestPrime(int number) {
		if (number < 0)
			return -1;

		int divisor = number / 2;

		if (isPrime(number))
			return number;

		while (divisor > 1) {
			if (number % divisor == 0 && isPrime(divisor))
				return divisor;

			divisor--;
		}

		return -1;
	}

	// Exercise 27
	public static void printSquareStar(int number) {
		if (number < 5) {
			System.out.println("Invalid Value");
			return;
		}

		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (i == 0 || i == number - 1 || j == 0 || j == number - 1 || i == j || j == number - i - 1) {
					System.out.print("*");
					continue;
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static void readingUserInputChallenge() {
		System.out.println("\nReading User Input Challenge");

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

	public static void minMaxChallenge() {
		System.out.println("\nMin and Max Challenge");

		Scanner scanner = new Scanner(System.in);

		double min = 0, max = 0, number;
		String input;
		int iter = 0;

		while (true) {
			System.out.print("Enter a Number: ");

			input = scanner.nextLine();

			try {
				number = Double.parseDouble(input);
			} catch (NumberFormatException exception) {
				break;
			}

			if (iter == 0)
				min = max = number;
			else if (number < min)
				min = number;
			else if (number > max)
				max = number;

			iter++;
		}
		scanner.close();

		if (iter > 0) {
			System.out.println("\nMin Number: " + min);
			System.out.println("Max Number: " + max);
		}
	}

	// Exercise 28
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

	// Exercise 29
	public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
		if (extraBuckets < 0 || width <= 0 || height <= 0 || areaPerBucket <= 0)
			return -1;

		double areaToCover = width * height;
		double areaBuckets = areaPerBucket * extraBuckets;

		return (int) Math.ceil((areaToCover - areaBuckets) / areaPerBucket);
	}

	public static int getBucketCount(double width, double height, double areaPerBucket) {
		if (width <= 0 || height <= 0 || areaPerBucket <= 0)
			return -1;

		double areaToCover = width * height;

		return (int) Math.ceil(areaToCover / areaPerBucket);
	}

	public static int getBucketCount(double area, double areaPerBucket) {
		if (area <= 0 || areaPerBucket <= 0)
			return -1;

		return (int) Math.ceil(area / areaPerBucket);
	}
}
