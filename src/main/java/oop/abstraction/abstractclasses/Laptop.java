package oop.abstraction.abstractclasses;

public class Laptop extends ProductForSale {
    public Laptop(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.printf("Ultimate LAPTOP at just $%.2f%n", getSalesPrice(1));
        System.out.println("-".repeat(50));
        System.out.println(description);
    }
}
