package basics.expressions;

// Exercise 5
public class LeapYearCalculator {
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999)
            return false;

        if (year % 4 != 0)
            return false;

        if (year % 100 != 0)
            return true;

        return year % 400 == 0;
    }
}
