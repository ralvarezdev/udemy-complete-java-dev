package generics.genericclasses;

public class Point implements Mappable {
	private double lat, lon;

	public Point(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	public Point(String coordinatesString) {
		double[] coordinates = Mappable.stringToLatLon(coordinatesString);
		this.lat = coordinates[0];
		this.lon = coordinates[1];
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	protected String getLocation() {
		return "[" + lat + ", " + lon + "]";
	}

	@Override
	public void render() {
		System.out.println(getLocation());
	}
}
