package basics.controlflow;

public class While {
	public static void main(String[] args) {
		While.challenge();
	}

	public static void challenge() {
		int i = 5, start = i;
		int evenCounter = 0;

		System.out.println("While Challenge");

		while (i <= 20 && evenCounter < 5)
			if (isEvenNumber(i++)) {
				System.out.println(i - 1);
				evenCounter++;
			}

		System.out.println("Amount of Even Numbers Found: " + evenCounter);
		System.out.println("Amoun of Odd Numbers Found: " + (isEvenNumber(start) ? evenCounter - 1 : evenCounter));
	}

	private static boolean isEvenNumber(int number) {
		return number % 2 == 0;
	}
}
