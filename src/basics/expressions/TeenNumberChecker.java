package basics.expressions;

// Exercise 8
public class TeenNumberChecker {
	public static boolean hasTeen(int teen1, int teen2, int teen3) {
		int teens[] = new int[] { teen1, teen2, teen3 };

		for (int i = 0; i < teens.length; i++)
			if (teens[i] >= 13 && teens[i] <= 19)
				return true;

		return false;
	}

	public static boolean isTeen(int teen) {
		return teen >= 13 && teen <= 19;
	}
}
