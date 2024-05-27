package oop.polymorphism;

public class ElectricCar extends Car {
	private double avgKmPerCharge;
	private int batterySize;

	public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
		super(description);

		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}

	@Override
	public void startEngine() {
		super.startEngine();

		System.out.println("Detected Electric Batery of " + batterySize + "kWh...");
	}

	@Override
	protected void runEngine() {
		System.out.println("Running Engine at a Duration per Charge of " + avgKmPerCharge + "Km...");
	}

}
