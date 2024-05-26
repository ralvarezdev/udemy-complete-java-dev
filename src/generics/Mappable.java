package generics;

public interface Mappable {
	abstract public void render();

	public static double[] stringToLatLon(String coordinatesString) {
		String[] coordinates = coordinatesString.split(",");
		double lat = Double.valueOf(coordinates[0]);
		double lon = Double.valueOf(coordinates[1]);

		return new double[] { lat, lon };
	}
}
