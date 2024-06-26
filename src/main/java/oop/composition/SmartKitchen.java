package oop.composition;

public class SmartKitchen {
    private final CoffeeMaker brewMaster;
    private final DishWasher dishWasher;
    private final Refrigerator iceBox;

    public SmartKitchen() {
        this(new CoffeeMaker(), new DishWasher(), new Refrigerator());
    }

    public SmartKitchen(CoffeeMaker brewMaster, DishWasher dishWasher, Refrigerator iceBox) {
        super();
        this.brewMaster = brewMaster;
        this.dishWasher = dishWasher;
        this.iceBox = iceBox;
    }

    public void addWater() {
        brewMaster.setHasWorkToDo(true);
    }

    public void pourMilk() {
        iceBox.setHasWorkToDo(true);
    }

    public void loadDishWasher() {
        dishWasher.setHasWorkToDo(true);
    }

    public void setKitchenState(boolean addWater, boolean pourMilk, boolean loadDishWasher) {
        if (addWater)
            addWater();

        if (pourMilk)
            pourMilk();

        if (loadDishWasher)
            loadDishWasher();
    }

    public void doKitchenWork() {
        brewMaster.brewCoffee();
        iceBox.orderFood();
        dishWasher.doDishes();
    }
}
