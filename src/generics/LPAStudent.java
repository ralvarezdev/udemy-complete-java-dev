package generics;

public class LPAStudent extends Student {

	private double percentComplete;

	public LPAStudent() {
		percentComplete = random.nextDouble(0, 100.001);
	}

	@Override
	public String toString() {
		return "%s %8.1f%%".formatted(super.toString(), percentComplete);
	}

	public double getPercentComplete() {
		return percentComplete;
	}

	@Override
	public boolean matchFieldValue(String fieldName, String value) {
		String fName = fieldName.toUpperCase();

		return switch (fName) {
		case "PERCENTCOMPLETE" -> percentComplete <= Double.parseDouble(value);
		default -> super.matchFieldValue(fieldName, value);
		};
	}
}
