package oop.abstraction.abstractclasses;

import java.util.LinkedList;

public class Store {
	private LinkedList<ProductForSale> products;

	public static void main(String[] args) {
		Store store = new Store();
		store.addItem(new Laptop("2 in 1", 500, "Acer 15\" with 8GB of RAM"));
		store.addItem(new Laptop("2 in 1", 650, "Acer 15\" with 16GB of RAM"));
		store.addItem(new Laptop("2 in 1", 850, "Acer 15\" with 32GB of RAM"));
		store.addItem(new Laptop("Ultrabook", 600, "Acer 15\" with 8GB of RAM"));
		store.addItem(new Laptop("Ultrabook", 750, "Acer 15\" with 16GB of RAM"));
		store.addItem(new Laptop("Ultrabook", 900, "Acer 15\" with 32GB of RAM"));

		LinkedList<OrderItem> orderItems = new LinkedList<>();

		for (int i = 0; i < store.getProducts().size(); i++) {
			System.out.println("ORDER N%010d".formatted(i + 1));

			store.addItemToOrder(orderItems, i, i * 2 + 1);
			store.printOrder(orderItems);
			System.out.println();
		}
	}

	public Store() {
		this.products = new LinkedList<ProductForSale>();
	}

	public LinkedList<ProductForSale> getProducts() {
		return products;
	}

	public void addItem(ProductForSale product) {
		products.add(product);
	}

	public void addItemToOrder(LinkedList<OrderItem> orderItems, int productIndex, int quantity) {
		orderItems.add(new OrderItem(products.get(productIndex), quantity));
	}

	public void printOrder(LinkedList<OrderItem> orderItems) {
		for (OrderItem orderItem : orderItems) {
			var product = orderItem.product();
			product.printPricedItem(orderItem.quantity());
		}
	}
}
