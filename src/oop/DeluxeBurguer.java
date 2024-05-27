package oop;

public class DeluxeBurguer extends Burguer {
	protected final static int DEFAULT_TOPPINGS = 5;
	protected static int MAX_TOPPINGS = DEFAULT_TOPPINGS;

	public DeluxeBurguer(String type, double price, Topping... toppings) {
		super(type, price, MAX_TOPPINGS, toppings);
	}

	@Override
	public String toString() {
		return "DeluxeBurguer [nToppings=" + nToppings + ", toppings=" + toppings + ", name=" + name + ", type=" + type
				+ ", price=" + price + "]";
	}
}
