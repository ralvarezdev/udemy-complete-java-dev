package oop;

public class MealOrder {
    private final Burger burger;
    private final Drink drink;
    private final SideItem sideItem;

    public MealOrder(Burger burger, Drink drink, SideItem sideItem) {
        this.burger = burger;
        this.drink = drink;
        this.sideItem = sideItem;
    }

    public void addTopping(Topping topping) {
        burger.addTopping(topping);
    }

    public void setDrinkSize(String size) {
        if (drink.isSizeValid(size))
            drink.setSize(size);
    }

    public double getTotalPrice() {
        return burger.getTotalPrice() + drink.getAdjustedPrice() + sideItem.getPrice();
    }

    @Override
    public String toString() {
        return "MealOrder [burger=" + burger.toString() + ", drink=" + drink.toString() + ", sideItem="
                + sideItem.toString() + "]";
    }

}
