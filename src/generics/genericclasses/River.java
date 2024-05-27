package generics.genericclasses;

import java.util.List;

public class River extends Line {
	private String name;

	public River(String name, List<Point> points) {
		super(points);
		this.name = name;
	}

	public River(String name, Point... points) {
		super(points);
		this.name = name;
	}

	public River(String name, String... coordinateStrings) {
		super(coordinateStrings);
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " River";
	}

	@Override
	public void render() {
		System.out.println(this);
		System.out.println("List of coordinates: " + super.getLocation());
	}
}
