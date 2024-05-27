package basics.controlflow;

public class EnhancedSwitch {
	public static void main(String[] args) {
		EnhancedSwitch.challenge();
	}

	public static void challenge() {
		System.out.println("Enhanced Switch Challenge");

		for (int i = 0; i <= 7; i++)
			System.out.println("Day " + i + " = " + dayOfTheWeek(i));
	}

	private static String dayOfTheWeek(int day) {
		return switch (day) {
		case 0 -> "Sunday";
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thursday";
		case 5 -> "Friday";
		case 6 -> "Saturday";
		default -> "Invalid Day";
		};
	}
}
