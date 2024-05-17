package polymorphism;

public class HybridCar extends Car {
	private double avgKmPerLitre;
	private double avgKmPerCharge;
	private int cylinder;
	private int batterySize;

	public HybridCar(String description, double avgKmPerLitre, double avgKmPerCharge, int cylinder, int batterySize) {
		super(description);

		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinder = cylinder;

		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}

	@Override
	public void startEngine() {
		super.startEngine();

		System.out.println("Detected Electric Batery of " + batterySize + "kWh...");
		System.out.println("Detected Gas Engine...");

	}

	@Override
	protected void runEngine() {
		System.out.println("Running Engine at a Duration per Charge of " + avgKmPerCharge + "Km");
		System.out.println("Running Engine of " + cylinder + " Cylinders at a Average Consumption of " + avgKmPerLitre
				+ "L per Km...");
	}

}
