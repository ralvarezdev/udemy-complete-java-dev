package datastructures.arrays;

import java.util.Arrays;
import java.util.Random;

public class ArrayChallenge {
	private final static int MIN_RANGE = 1;
	private final static int MAX_RANGE = 1000;

	private int[] randomNumbers;
	@SuppressWarnings("unused")
	private int nNumbers;

	public static void main(String[] args) {
		ArrayChallenge arrayChallenge = new ArrayChallenge(10);
		arrayChallenge.printNumbers();
	}

	public ArrayChallenge(int nNumbers) {
		Random random = new Random();

		this.nNumbers = nNumbers;
		randomNumbers = new int[nNumbers];

		for (int i = 0; i < nNumbers; i++)
			randomNumbers[i] = random.nextInt(MIN_RANGE, MAX_RANGE + 1);
	}

	private void printArray(int[] numbers) {
		for (int i : numbers)
			System.out.print(i + " ");

		System.out.println();
	}

	public void printNumbers() {
		System.out.println("Number before Sorting:");
		printArray(randomNumbers);

		int[] sortNumbers = randomNumbers.clone();
		Arrays.sort(sortNumbers);

		System.out.println("\nNumber after Sorting:");
		printArray(sortNumbers);
	}
}
