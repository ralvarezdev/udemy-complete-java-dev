package oop;

public class InheritanceCuboid extends InheritanceRectangle {
	private double height;

	public InheritanceCuboid(double width, double length, double height) {
		super(width, length);
		this.height = height < 0 ? 0 : height;
	}

	public double getHeight() {
		return height;
	}

	public double getVolume() {
		return getArea() * height;
	}
}
