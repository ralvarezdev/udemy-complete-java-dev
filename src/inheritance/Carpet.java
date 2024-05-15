package oop;

public class InheritanceCarpet {
	private double cost;

	public InheritanceCarpet(double cost) {
		this.cost = cost < 0 ? 0 : cost;
	}

	public double getCost() {
		return cost;
	}
}
