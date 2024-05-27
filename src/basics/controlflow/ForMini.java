package basics.controlflow;

public class ForMini {
	public static void main(String[] args) {
		ForMini.challenge();
	}

	public static void challenge() {
		double interest;
		final double AMOUNT = 100;

		System.out.println("For Mini Challenge");

		for (double i = 7.5; i <= 10.0; i += 0.25) {
			interest = calculateInterest(AMOUNT, i);
			System.out.println("Interest (" + i + "%) for " + AMOUNT + "$ = " + interest);
		}
	}

	private static double calculateInterest(double amount, double interestRate) {
		return amount * interestRate / 100;
	}
}
