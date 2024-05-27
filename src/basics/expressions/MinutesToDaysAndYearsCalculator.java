package basics.expressions;

// Exercise 10
public class MinutesToDaysAndYearsCalculator {
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
}
