package oop.abstraction.abstractclasses;

import java.util.Formatter;

public class Wine extends ProductForSale {
	public Wine(String type, double price, String description) {
		super(type, price, description);
	}

	@Override
	public void showDetails() {
		Formatter formatter = new Formatter();

		System.out.println(formatter.format("Delicious and elegant WINE at just $%.2f", this.getSalesPrice(1)));
		System.out.println("-".repeat(50));
		System.out.println(description);
	}
}
