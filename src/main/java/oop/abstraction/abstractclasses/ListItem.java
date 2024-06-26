package oop.abstraction.abstractclasses;

public abstract class ListItem<T extends Comparable<T>> {
    protected ListItem<T> rightLink;
    protected ListItem<T> leftLink;
    protected T value;

    public ListItem(T value) {
        this.value = value;
    }

    abstract ListItem<T> next();

    abstract ListItem<T> setNext(ListItem<T> rightLink);

    abstract ListItem<T> previous();

    abstract ListItem<T> setPrevious(ListItem<T> leftLink);

    abstract int compareTo(ListItem<T> item);

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}