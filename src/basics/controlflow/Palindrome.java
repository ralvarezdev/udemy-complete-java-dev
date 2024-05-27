package basics.controlflow;

// Exercise 16
public class Palindrome {
	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++)
			System.out.println("Is %-7d perfect? %s".formatted(i, isPalindrome(i) ? "Yes" : "No"));
	}

	public static boolean isPalindrome(int number) {
		String numberAbs = "" + Math.abs(number);
		StringBuilder numberStringReverse = new StringBuilder(numberAbs).reverse();

		return numberAbs.equals(numberStringReverse.toString());
	}
}
