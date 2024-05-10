package basics;

public class FirstSteps {
	public static void main(String[] args) {
		primitiveTypesChallenge();
		floatingPointChallenge();
		charChallenge();
		operatorChallenge();
	}

	public static void primitiveTypesChallenge() {
		byte var1 = Byte.MAX_VALUE;
		short var2 = Short.MAX_VALUE;
		int var3 = 10000000;
		long var4 = 50000L * (var1 + var2 + var3);

		System.out.println("Primitive Types Challenge");
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4);
	}

	public static void floatingPointChallenge() {
		double pounds = 200;
		double kilograms = pounds / 2.20462;

		System.out.println("\nFloating Point Precision Challenge");
		System.out.println("Pounds: " + pounds);
		System.out.println("Kilograms: " + kilograms);
	}

	public static void charChallenge() {
		char mySimpleChar = '?';
		char myUnicodeChar = '\u003f';
		char myDecimalChar = 63;

		System.out.println("\nChar Challenge");
		System.out.println("My values are: " + mySimpleChar + myUnicodeChar + myDecimalChar);
	}

	public static void operatorChallenge() {
		double var1 = 200.0;
		double var2 = 80.0;
		double r1 = 100.0 * (var1 + var2);
		double remainder = r1 % 40.0;

		boolean isMultiple = remainder == 0.0;

		System.out.println("\nOperator Challenge");
		System.out.println("Remainder: " + remainder);

		if (!isMultiple)
			System.out.println("Got Some Remainder");
	}
}
