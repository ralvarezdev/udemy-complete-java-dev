package oop.abstraction.abstractclasses;

public interface NodeList<T extends Comparable<T>> {
	ListItem<T> getRoot();

	boolean addItem(ListItem<T> item);

	boolean removeItem(ListItem<T> item);

	void traverse(ListItem<T> root);
}