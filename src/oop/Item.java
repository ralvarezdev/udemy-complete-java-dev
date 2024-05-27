package oop;

public class Item {
	protected String name;
	protected String type;
	protected double price;

	public Item(String name, String type, double price) {
		this.name = name.toLowerCase();
		this.type = type.toLowerCase();
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", type=" + type + ", price=" + price + "]";
	}
}
