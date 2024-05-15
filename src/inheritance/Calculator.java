package oop;

public class InheritanceCalculator {
	private InheritanceFloor floor;
	private InheritanceCarpet carpet;

	public InheritanceCalculator(InheritanceFloor floor, InheritanceCarpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}

	public double getTotalCost() {
		return floor.getArea() * carpet.getCost();
	}
}
