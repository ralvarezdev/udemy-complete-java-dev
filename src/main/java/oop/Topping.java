package oop;

public class Topping extends Item {
    public Topping(String type, double price) {
        super("Topping", type, price);
    }

    @Override
    public String toString() {
        return "Topping [name=" + name + ", type=" + type + ", price=" + price + "]";
    }
}
