package nestedclasses.innerclasses;

import java.util.LinkedList;

public class Meal {
	private double price = 5.0;
	private Burger burger;
	private Item drink;
	private Item side;

	private double conversionRate;
	private static int DEF_CONV = 1;

	public Meal() {
		this(DEF_CONV);
	}

	public Meal(double conversionRate) {
		this.conversionRate = conversionRate;
		burger = new Burger("regular");
		drink = new Item("coke", "drink", 1.5);
		side = new Item("fries", "side", 2.0);
	}

	public double getTotal() {
		double total = burger.getTotal() + drink.price + side.price;
		return Item.getPrice(total, conversionRate);
	}

	@Override
	public String toString() {
		return "%s%n%s%n%s%nTotal due: %.2f".formatted(burger, drink, side, getTotal());
	}

	private class Item {
		protected String name;
		protected String type;
		protected double price;

		public Item() {
			this(null, null);
		}

		public Item(String name, String type) {
			this(name, type, 0);
		}

		public Item(String name, String type, double price) {
			this.name = name;
			this.type = type;
			this.price = price;
		}

		@Override
		public String toString() {
			return "%-10s | %-15s | $%.2f".formatted(type, name, getPrice(price, conversionRate));
		}

		protected static double getPrice(double price, double rate) {
			return price * rate;
		}
	}

	private class Burger extends Item {
		private enum PosTopping {
			ONION, CHEESE, AVOCADO, BACON, KETCHUP, MUSTARD, MAYONAISSE, PICKLES;

			double getPrice() {
				return switch (this) {
				case CHEESE, BACON -> 1.5;
				case AVOCADO, ONION -> 1;
				default -> 0;
				};
			}
		}

		private LinkedList<Item> toppings;

		public Burger(String name, String... toppings) {
			this(name, Meal.this.price, toppings);
		}

		public Burger(String name, double price, String... toppings) {
			super(name, "burger", price);

			this.toppings = new LinkedList<>();
			for (String toppingName : toppings)
				addTopping(toppingName);
		}

		public boolean addTopping(String name) {
			if (name == null)
				return false;

			try {
				PosTopping topping = PosTopping.valueOf(name.toUpperCase());
				this.toppings.add(new Item(topping.name(), "topping", topping.getPrice()));
			} catch (IllegalArgumentException e) {
				System.out.println(name + " topping not found!");
			}
			return true;
		}

		public double getTotal() {
			double totalPrice = this.price;

			for (Item topping : toppings)
				totalPrice += topping.price;

			return totalPrice;
		}

		@Override
		public String toString() {
			String temp = super.toString();

			for (Item topping : toppings)
				temp += "%n%s".formatted(topping.toString());

			return temp;
		}
	}

	public boolean addTopping(String name) {
		return burger.addTopping(name);
	}
}
