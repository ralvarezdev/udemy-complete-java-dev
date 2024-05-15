package oop;

public class InheritanceCircle {
	private double radius;

	public InheritanceCircle(double radius) {
		this.radius = radius < 0 ? 0 : radius;
	}

	public double getRadius() {
		return radius;
	}

	public double getArea() {
		return radius * radius * Math.PI;
	}
}
