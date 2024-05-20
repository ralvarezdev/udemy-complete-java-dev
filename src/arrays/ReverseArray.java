package arrays;

import java.util.Arrays;

public class ReverseArray {
	@SuppressWarnings("unused")
	private static void reverse(int[] array) {
		int length = array.length;
		int temp;

		System.out.println("Array = " + Arrays.toString(array));

		for (int i = 0; i < length / 2; i++) {
			temp = array[i];
			array[i] = array[length - i - 1];
			array[length - i - 1] = temp;
		}

		System.out.println("Reversed array = " + Arrays.toString(array));
	}
}