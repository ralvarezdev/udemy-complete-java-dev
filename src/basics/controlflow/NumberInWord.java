package basics.controlflow;

// Exercise 13
public class NumberInWord {
	public static void main(String[] args) {
		for (int i = -1; i <= 10; i++) {
			System.out.print("%-5d = ".formatted(i));
			NumberInWord.printNumberInWord(i);
		}
	}

	public static void printNumberInWord(int number) {
		String numberStr = switch (number) {
		case 0 -> "ZERO";
		case 1 -> "ONE";
		case 2 -> "TWO";
		case 3 -> "THREE";
		case 4 -> "FOUR";
		case 5 -> "FIVE";
		case 6 -> "SIX";
		case 7 -> "SEVEN";
		case 8 -> "EIGHT";
		case 9 -> "NINE";
		default -> "OTHER";
		};

		System.out.println(numberStr);
	}
}
