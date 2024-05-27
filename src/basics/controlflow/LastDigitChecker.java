package basics.controlflow;

// Exercise 20
public class LastDigitChecker {
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
}
