package oop.polymorphism;

public class GasPoweredCar extends Car {
	private double avgKmPerLitre;
	private int cylinder;

	public GasPoweredCar(String description, double avgKmPerLitre, int cylinder) {
		super(description);

		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinder = cylinder;
	}

	@Override
	public void startEngine() {
		super.startEngine();

		System.out.println("Detected Gas Engine...");
	}

	@Override
	protected void runEngine() {
		System.out.println("Running Engine of " + cylinder + " Cylinders at a Average Consumption of " + avgKmPerLitre
				+ "L per Km...");
	}
}
