package basics.controlflow;

// Exercise 17
public class SumFirstAndLast {
	public static int sumFirstAndLastDigit(int number) {
		if (number < 0)
			return -1;

		String numberString = "" + number;
		char first = numberString.charAt(0);
		char last = numberString.charAt(numberString.length() - 1);

		return Character.getNumericValue(first) + Character.getNumericValue(last);
	}
}
