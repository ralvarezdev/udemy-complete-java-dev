package basics.expressions;

// Exercise 6
public class DecimalComparator {
	public static boolean areEqualByThreeDecimalPlaces(double number1, double number2) {
		long convNumber1 = (long) (number1 * 1000);
		long convNumber2 = (long) (number2 * 1000);

		return convNumber1 == convNumber2;
	}

}
