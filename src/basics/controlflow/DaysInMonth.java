package basics.controlflow;

import basics.expressions.LeapYearCalculator;

// Exercise 14
public class DaysInMonth {
	public static void main(String[] args) {
		int year, month;

		for (int i = 1; i <= 2024; i++) {
			year = i;
			month = i % 12 + 1;

			System.out.print("Year %-4d, Month %-2d = ".formatted(year, month));
			System.out.println(DaysInMonth.getDaysInMonth(month, year));
		}
	}

	public static int getDaysInMonth(int month, int year) {
		if (month < 1 || month > 12 || year < 1 || year > 9999)
			return -1;

		if (LeapYearCalculator.isLeapYear(year) && month == 2)
			return 29;

		return switch (month) {
		case 2 -> 28;
		case 1, 3, 5, 7, 8, 10, 12 -> 31;
		case 4, 6, 9, 11 -> 30;
		default -> -1;
		};
	}
}