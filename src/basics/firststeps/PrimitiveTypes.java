package basics.firststeps;

public class PrimitiveTypes {
	public static void main(String[] args) {
		PrimitiveTypes.challenge();
	}

	public static void challenge() {
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
}
