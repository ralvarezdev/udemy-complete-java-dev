package basics.controlflow;

// Exercise 27
public class SquareStar {
	public static void main(String[] args) {
		for (int i = 4; i < 10; i++) {
			SquareStar.printSquareStar(i);
			System.out.println();
		}
	}

	public static void printSquareStar(int number) {
		if (number < 5) {
			System.out.println("Invalid Value");
			return;
		}

		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (i == 0 || i == number - 1 || j == 0 || j == number - 1 || i == j || j == number - i - 1) {
					System.out.print("*");
					continue;
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
