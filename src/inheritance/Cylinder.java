package oop;

public class InheritanceCylinder extends InheritanceCircle {
	private double height;

	public InheritanceCylinder(double radius, double height) {
		super(radius);
		this.height = height < 0 ? 0 : height;
	}

	public double getHeight() {
		return height;
	}

	public double getVolume() {
		return getArea() * height;
	}
}
