package abstraction;

public class Beef extends ProductForSale {
	public Beef(String type, double price, String description) {
		super(type, price, description);
	}

	@Override
	public void showDetails() {
		System.out.println("Delicious BEEF at just $%.2f".formatted(getSalesPrice(1)));
		System.out.println("-".repeat(50));
		System.out.println(description);
	}
}
