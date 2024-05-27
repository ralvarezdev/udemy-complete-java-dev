package basics.expressions;

// Exercise 2
public class SpeedConverter {
	public static long toMilesPerHour(double kilometersPerHour) {
		final double MILE_TO_KM = 1.609;

		if (kilometersPerHour < 0)
			return -1;

		long milesPerHour = (long) (Math.round(kilometersPerHour / MILE_TO_KM));

		return milesPerHour;
	}

	public static void printConversion(double kilometersPerHour) {
		if (kilometersPerHour < 0) {
			System.out.print("Invalid Value");
			return;
		}

		System.out.println("" + kilometersPerHour + " km/h = " + toMilesPerHour(kilometersPerHour) + " mi/h");
	}

}
