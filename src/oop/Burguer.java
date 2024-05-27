package oop;

import java.util.ArrayList;

public class Burguer extends Item {
	private final static int DEFAULT_TOPPINGS = 3;
	protected static int MAX_TOPPINGS = DEFAULT_TOPPINGS;

	protected int nToppings = 0;
	protected ArrayList<Topping> toppings = new ArrayList<>();

	public Burguer(String type, double price, Topping... toppings) {
		super("Burguer", type, price);

		MAX_TOPPINGS = DEFAULT_TOPPINGS;
		addToppings(toppings);
	}

	protected Burguer(String type, double price, int maxToppings, Topping... toppings) {
		super("Burguer", type, price);

		MAX_TOPPINGS = maxToppings;
		addToppings(toppings);
	}

	public double getTotalPrice() {
		double totalPrice = price;

		for (int i = 0; i < nToppings; i++)
			totalPrice += toppings.get(i).getPrice();

		return totalPrice;
	}

	public void addTopping(Topping topping) {
		if (nToppings >= MAX_TOPPINGS)
			return;

		toppings.add(nToppings++, topping);
	}

	public void addToppings(Topping[] toppings) {
		for (Topping topping : toppings)
			addTopping(topping);
	}

	@Override
	public String toString() {
		return "Burguer [nToppings=" + nToppings + ", toppings=" + toppings + ", name=" + name + ", type=" + type
				+ ", price=" + price + "]";
	}

}
