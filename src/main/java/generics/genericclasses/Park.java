package generics.genericclasses;

public class Park extends Point {
    private final String name;

    public Park(String name, double lat, double lon) {
        super(lat, lon);
        this.name = name;
    }

    public Park(String name, String coordinatesString) {
        super(coordinatesString);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " Park";
    }

    @Override
    public void render() {
        System.out.println(this);
        System.out.println("Coordinates: " + super.getLocation());
    }
}
