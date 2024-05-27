package basics.controlflow;

public class For {
	public static void main(String[] args) {
		For.challenge();
	}

	public static void challenge() {
		boolean isPrime;
		int primeCounter = 0;

		System.out.println("For Challenge\nY (Prime Number)\nN (not a Prime Number)\n");

		for (int i = 0; i <= 1000; i++) {
			isPrime = isPrime(i);

			System.out.println("" + i + (isPrime ? " Y" : " N"));

			if (isPrime)
				primeCounter++;
		}

		System.out.println("\nNumber of Prime Number: " + primeCounter);
	}

	public static boolean isPrime(int wholeNumber) {
		if (wholeNumber <= 2)
			return wholeNumber == 2;

		for (int divisor = 2; divisor <= wholeNumber / 2; divisor++)
			if (wholeNumber % divisor == 0)
				return false;

		return true;
	}

}
