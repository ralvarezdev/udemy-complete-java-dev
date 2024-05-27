package basics.controlflow;

// Exercise 21
public class AllFactors {
	public static void printFactors(int number) {
		if (number < 1) {
			System.out.print("Invalid Value");
			return;
		}

		for (int i = 1; i <= number / 2; i++)
			if (number % i == 0)
				System.out.printf("%d ", i);
		System.out.printf("%d ", number);
	}
}
