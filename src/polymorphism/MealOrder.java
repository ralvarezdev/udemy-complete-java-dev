package polymorphism;

public class MealOrder {
	private Burguer burguer;
	private Drink drink;
	private SideItem sideItem;

	public MealOrder(Burguer burguer, Drink drink, SideItem sideItem) {
		this.burguer = burguer;
		this.drink = drink;
		this.sideItem = sideItem;
	}

	public void addTopping(Topping topping) {
		burguer.addTopping(topping);
	}

	public void setDrinkSize(String size) {
		if (drink.isSizeValid(size))
			drink.setSize(size);
	}

	public double getTotalPrice() {
		return burguer.getTotalPrice() + drink.getAdjustedPrice() + sideItem.getPrice();
	}

	@Override
	public String toString() {
		return "MealOrder [burguer=" + burguer.toString() + ", drink=" + drink.toString() + ", sideItem="
				+ sideItem.toString() + "]";
	}

}
