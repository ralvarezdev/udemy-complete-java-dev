package oop.inheritance;

public class Point {
	private int x;
	private int y;

	public Point() {
		return;
	}

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double distance() {
		return distance(0, 0);
	}

	public double distance(Point point) {
		return distance(point.getX(), point.getY());
	}

	public double distance(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}
}
