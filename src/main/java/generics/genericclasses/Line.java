package generics.genericclasses;

import java.util.LinkedList;
import java.util.List;

public class Line implements Mappable {
    private LinkedList<Point> points;

    public Line() {
        this.points = new LinkedList<>();
    }

    public Line(List<Point> points) {
        this();
        addPoints(points);
    }

    public Line(Point... points) {
        this();
        addPoints(points);
    }

    public Line(String... coordinatesStrings) {
        this();
        addPoints(coordinatesStrings);
    }

    public boolean addPoint(double lat, double lon) {
        if (findPoint(lat, lon) != null)
            return false;

        points.add(new Point(lat, lon));
        return true;
    }

    public boolean addPoint(Point point) {
        if (point == null)
            return false;

        return addPoint(point.getLat(), point.getLon());
    }

    public boolean addPoint(String coordinatesString) {
        if (findPoint(coordinatesString) != null)
            return false;

        points.add(new Point(coordinatesString));
        return true;
    }

    public boolean addPoints(List<Point> points) {
        if (points == null)
            return false;

        for (Point point : points)
            addPoint(point);

        return true;
    }

    public boolean addPoints(Point... points) {
        for (Point point : points)
            addPoint(point);

        return true;
    }

    public boolean addPoints(String... coordinatesStrings) {
        for (String coordinarteString : coordinatesStrings)
            addPoint(new Point(coordinarteString));

        return true;
    }

    public Point findPoint(String coordinatesString) {
        double[] coordinates = Mappable.stringToLatLon(coordinatesString);

        return findPoint(coordinates[0], coordinates[1]);
    }

    public Point findPoint(double lat, double lon) {
        for (Point point : points)
            if (point.getLat() == lat && point.getLon() == lon)
                return point;

        return null;
    }

    protected String getLocation() {
        String locationString = "";
        int i = 1;

        for (Point point : points) {
            if (i != 1)
                locationString += ", ";

            locationString = locationString + i++ + " = [" + point.getLat() + ", " + point.getLon() + "]";
        }

        return locationString;
    }

    @Override
    public void render() {
        System.out.println(getLocation());
    }
}
