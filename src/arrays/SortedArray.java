package arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortedArray {
	public SortedArray() {
		return;
	}

	public static int[] getIntegers(int length) {
		int[] array = new int[length];

		Scanner scanner = new Scanner(System.in);
		int i = 0, number;

		while (i < length) {
			System.out.print("Enter a number: ");
			number = scanner.nextInt();
			array[i++] = number;
		}
		scanner.close();

		return array;
	}

	public static void printArray(int[] array) {
		int i = 0;

		for (int number : array)
			System.out.println("Element " + i++ + " contents " + number);
	}

	public static int[] sortIntegers(int[] array) {
		List<Integer> list = new ArrayList<Integer>();

		for (int number : array)
			list.add(number);

		list.sort(Comparator.naturalOrder());
		list.sort(Comparator.reverseOrder());

		Integer[] sortedIntegerArray = new Integer[list.size()];
		list.toArray(sortedIntegerArray);

		int[] sortedIntArray = new int[list.size()];
		int i = 0;

		for (int number : sortedIntegerArray)
			sortedIntArray[i++] = number;

		return sortedIntArray;
	}
}