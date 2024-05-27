package basics.controlflow;

public class Sum3And5 {
	public static void main(String[] args) {
		Sum3And5.challenge();
	}

	public static void challenge() {
		int matches = 0;
		int sumMatches = 0;

		System.out.println("Sum 3 and 5 Challenge");

		for (int i = 1; i <= 1000 && matches < 5; i++)
			if (i % 3 == 0 && i % 5 == 0) {
				matches++;
				sumMatches += i;

				System.out.println(i);
			}

		System.out.println("Sum of Matches: " + sumMatches);
	}
}
