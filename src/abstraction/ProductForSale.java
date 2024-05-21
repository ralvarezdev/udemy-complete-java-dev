package abstraction;

public abstract class ProductForSale {
	protected String type;
	protected double price;
	protected String description;

	public ProductForSale(String type, double price, String description) {
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public double getSalesPrice(int quantity) {
		return quantity * price;
	}

	public void printPricedItem(int quantity) {
		System.out.println("%-20s | %-4d x $%-8.2f | %10.2f".formatted(type, quantity, price, getSalesPrice(quantity)));
	}

	public abstract void showDetails();
}
