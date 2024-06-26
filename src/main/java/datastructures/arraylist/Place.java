package datastructures.arraylist;

public class Place {
    private String name;
    private int distance;

    public Place(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public Place(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
