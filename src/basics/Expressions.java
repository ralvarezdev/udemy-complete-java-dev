package basics;

public class Expressions {
	public static void main(String[] args) {
		ifThenElseChallenge();
		methodChalenge();
		overloadedMethodChallenge();
		secondsAndMinutesChallenge();
	}

	public static void ifThenElseChallenge() {
		boolean gameOver = true;
		int score = 800;
		int levelCompleted = 5;
		int bonus = 100;
		int finalScore;

		System.out.println("If-then-else Challenge");

		if (gameOver) {
			finalScore = calculateScore(score, levelCompleted, bonus);
			System.out.println("Your final score was " + finalScore);
		}

		score = 10000;
		levelCompleted = 8;
		bonus = 200;

		if (gameOver) {
			finalScore = calculateScore(score, levelCompleted, bonus);
			System.out.println("Your final score was " + finalScore);
		}
	}

	private static int calculateScore(int score, int levelCompleted, int bonus) {
		return score + levelCompleted * bonus + 1000;
	}

	public static void methodChalenge() {
		int testHighScore[] = new int[] { 1500, 1000, 500, 100, 25 };
		String testName[] = new String[] { "Tim", "Bob", "Percy", "Gilbert", "James" };
		int pos, score;

		System.out.println("\nMethod Challenge");

		for (int i = 0; i < testHighScore.length; i++) {
			score = testHighScore[i];
			pos = calculateHighScorePosition(score);

			displayHighScorePosition(testName[i], pos);
		}
	}

	private static void displayHighScorePosition(String name, int pos) {
		if (pos <= 0)
			return;

		System.out.println(name + " managed to get into position " + pos + " on the high score list");
	}

	private static int calculateHighScorePosition(int score) {
		if (score < 100)
			return 4;
		if (score < 500)
			return 3;
		if (score < 1000)
			return 2;

		return 1;
	}

	// Exercise 1
	public static void checkNumber(int number) {
		if (number > 0)
			System.out.print("positive");
		else if (number < 0)
			System.out.print("negative");
		else
			System.out.print("zero");
	}

	// Exercise 2 Part I
	public static long toMilesPerHour(double kilometersPerHour) {
		final double MILE_TO_KM = 1.609;

		if (kilometersPerHour < 0)
			return -1;

		long milesPerHour = (long) (Math.round(kilometersPerHour / MILE_TO_KM));

		return milesPerHour;
	}

	// Exercise 2 Part II
	public static void printConversion(double kilometersPerHour) {
		if (kilometersPerHour < 0) {
			System.out.print("Invalid Value");
			return;
		}

		System.out.println("" + kilometersPerHour + " km/h = " + toMilesPerHour(kilometersPerHour) + " mi/h");
	}

	// Exercise 3
	public static void printMegaBytesAndKiloBytes(int kiloBytes) {
		if (kiloBytes < 0) {
			System.out.println("Invalid Value");
			return;
		}

		final int MB_TO_KB = 1024;

		int convMegaBytes = kiloBytes / MB_TO_KB;
		int convKiloBytes = kiloBytes % MB_TO_KB;
8
		System.out.println("" + kiloBytes + " KB = " + convMegaBytes + " MB and " + convKiloBytes + " KB");
	}

	// Exercise 4
	public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
		if (hourOfDay < 0 || hourOfDay > 23)
			return false;

		if (barking && (hourOfDay < 8 || hourOfDay > 22))
			return true;

		return false;
	}

	// Exercise 5
	public static boolean isLeapYear(int year) {
		if (year < 1 || year > 9999)
			return false;

		if (year % 4 != 0)
			return false;

		if (year % 100 != 0)
			return true;

		if (year % 400 == 0)
			return true;

		return false;
	}

	// Exercise 6
	public static boolean areEqualByThreeDecimalPlaces(double number1, double number2) {
		long convNumber1 = (long) (number1 * 1000);
		long convNumber2 = (long) (number2 * 1000);

		return convNumber1 == convNumber2;
	}

	// Exercise 7
	public static boolean hasEqualSum(int number1, int number2, int number3) {
		return number1 + number2 == number3;
	}

	// Exercise 8 Part I
	public static boolean hasTeen(int teen1, int teen2, int teen3) {
		int teens[] = new int[] { teen1, teen2, teen3 };

		for (int i = 0; i < teens.length; i++)
			if (teens[i] >= 13 && teens[i] <= 19)
				return true;

		return false;
	}

	// Exercise 8 Part II
	public static boolean isTeen(int teen) {
		return teen >= 13 && teen <= 19;
	}

	public static void overloadedMethodChallenge() {
		int foot, inches;
		double centimeters;

		System.out.println("\nOverloaded Method Challenge");

		for (int i = 0; i < 5; i++) {
			centimeters = convertToCentimeters(i);
			System.out.println("" + i + " inches = " + centimeters + " cm");
		}

		foot = 5;
		inches = 8;
		centimeters = convertToCentimeters(foot, inches);
		System.out.println("" + foot + " foot and " + inches + " inches = " + centimeters + " cm");
	}

	private static double convertToCentimeters(int inches) {
		final double INCH_TO_CM = 2.54;

		return inches * INCH_TO_CM;
	}

	// Overloaded Method
	private static double convertToCentimeters(int foot, int inches) {
		final int FEET_TO_INCH = 12;

		return convertToCentimeters(foot * FEET_TO_INCH + inches);
	}

	public static void secondsAndMinutesChallenge() {
		System.out.println("\nSeconds and Minutes Challenge");

		String duration = getDurationString(8432);
		String overloadedDuration = getDurationString(72, 54);

		System.out.println(duration);
		System.out.println(overloadedDuration);
	}

	private static String getDurationString(int seconds) {
		if (seconds < 0)
			return "Invalid Value";

		final int MIN_TO_SEC = 60;
		final int HOUR_TO_MIN = 60;

		int remainingSeconds = seconds % MIN_TO_SEC;
		int minutes = seconds / MIN_TO_SEC;

		int remainingMinutes = minutes % HOUR_TO_MIN;
		int hours = minutes / HOUR_TO_MIN;

		return "" + hours + "h " + remainingMinutes + "m " + remainingSeconds + "s";
	}

	// Overloaded Method
	private static String getDurationString(int minutes, int seconds) {
		if (minutes < 0 || seconds < 0 || seconds > 59)
			return "Invalid Value";

		final int MIN_TO_SEC = 60;

		return getDurationString(minutes * MIN_TO_SEC + seconds);
	}

	// Exercise 9 Part I
	public static double area(double radius) {
		if (radius < 0)
			return -1.0;

		final double PI = 3.14159;

		return radius * radius * PI;
	}

	// Exercise 9 Part II
	public static double area(double x, double y) {
		if (x < 0 || y < 0)
			return -1.0;

		return x * y;
	}

	// Exercise 10
	public static void printYearsAndDays(long minutes) {
		if (minutes < 0) {
			System.out.println("Invalid Value");
			return;
		}

		final int HOUR_TO_MIN = 60;
		final int DAY_TO_HOUR = 24;
		final int DAY_TO_MIN = DAY_TO_HOUR * HOUR_TO_MIN;
		final int YEAR_TO_DAY = 365;

		long days = (long) minutes / DAY_TO_MIN;
		long remainingDays = (long) days % YEAR_TO_DAY;
		long years = (long) days / YEAR_TO_DAY;

		System.out.println("" + minutes + " min = " + years + " y and " + remainingDays + " d");
	}

	// Exercise 11
	public static void printEqual(int number1, int number2, int number3) {
		if (number1 < 0 || number2 < 0 || number3 < 0)
			System.out.println("Invalid Value");
		else if (number1 == number2 && number2 == number3)
			System.out.println("All numbers are equal");
		else if (number1 != number2 && number2 != number3 && number1 != number3)
			System.out.println("All numbers are different");
		else
			System.out.println("Neither all are equal or different");
	}

	// Exercise 12
	public static boolean isCatPlaying(boolean summer, int temperature) {
		if (temperature < 25)
			return false;

		if (summer)
			return temperature <= 45;
		return temperature <= 35;
	}
}