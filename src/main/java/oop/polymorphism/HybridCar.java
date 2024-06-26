package oop.polymorphism;

public class HybridCar extends Car {
    private final double avgKmPerLitre;
    private final double avgKmPerCharge;
    private final int cylinder;
    private final int batterySize;

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

        System.out.println("Detected Electric Battery of " + batterySize + "kWh...");
        System.out.println("Detected Gas Engine...");

    }

    @Override
    protected void runEngine() {
        System.out.println("Running Engine at a Duration per Charge of " + avgKmPerCharge + "Km");
        System.out.println("Running Engine of " + cylinder + " Cylinders at a Average Consumption of " + avgKmPerLitre
                + "L per Km...");
    }

}
