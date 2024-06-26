package oop.abstraction.interfaces;

import java.util.LinkedList;
import java.util.List;

public class Monster implements ISaveable {
    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public List<String> write() {
        LinkedList<String> list = new LinkedList<>();

        list.add(name);
        list.add("" + hitPoints);
        list.add("" + strength);

        return list;
    }

    @Override
    public void read(List<String> list) {
        if (list == null || list.isEmpty())
            return;

        try {
            name = list.get(0);
            hitPoints = Integer.parseInt(list.get(1));
            strength = Integer.parseInt(list.get(2));
        } catch (IndexOutOfBoundsException _) {
        }
    }

    @Override
    public String toString() {
        return "Monster{name='" + name + "', hitPoints=" + hitPoints + ", strength=" + strength + "}";
    }
}
