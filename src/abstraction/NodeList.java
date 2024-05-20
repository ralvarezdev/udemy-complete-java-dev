package abstraction;

public interface NodeList<T extends Comparable<T>> {
	public abstract ListItem<T> getRoot();

	public abstract boolean addItem(ListItem<T> item);

	public abstract boolean removeItem(ListItem<T> item);

	public abstract void traverse(ListItem<T> root);
}