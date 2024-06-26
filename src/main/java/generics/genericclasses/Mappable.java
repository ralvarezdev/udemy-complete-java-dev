package generics.genericclasses;

public interface Mappable {
    public void render();

    public static double[] stringToLatLon(String coordinatesString) {
        String[] coordinates = coordinatesString.split(",");
        double lat = Double.parseDouble(coordinates[0]);
        double lon = Double.parseDouble(coordinates[1]);

        return new double[]{lat, lon};
    }
}
