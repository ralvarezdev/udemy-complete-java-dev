package oop;

public class Drink extends Item {
	private final static String SMALL_SIZE = "small";
	private final static String MEDIUM_SIZE = "medium";
	private final static String LARGE_SIZE = "large";
	private final static String BIG_SIZE = "big";
	private final static String DEFAULT_SIZE = SMALL_SIZE;

	private String size;

	public Drink(String type, double price, String size) {
		super("Drink", type, price);
		setSize(size);
	}

	@Override
	public String getName() {
		return size + " " + name;
	}

	public boolean isSizeValid(String size) {
		return (size == SMALL_SIZE || size == MEDIUM_SIZE || size == LARGE_SIZE || size == BIG_SIZE);
	}

	public void setSize(String size) {
		if (isSizeValid(size)) {
			this.size = size;
			return;
		}

		this.size = DEFAULT_SIZE;
	}

	public double getAdjustedPrice() {
		return switch (size) {
		case SMALL_SIZE -> price;
		case MEDIUM_SIZE -> price * 1.25;
		case LARGE_SIZE -> price * 1.5;
		case BIG_SIZE -> price * 2;
		default -> -1;
		};
	}

	@Override
	public String toString() {
		return "Drink [size=" + size + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
}
