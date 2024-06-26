package oop;

public class SideItem extends Item {
    public SideItem(String type, double price) {
        super("Side Item", type, price);
    }

    @Override
    public String toString() {
        return "SideItem [name=" + name + ", type=" + type + ", price=" + price + "]";
    }
}
