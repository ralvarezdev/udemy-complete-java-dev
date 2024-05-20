package abstraction;

public class Node<T extends Comparable<T>> extends ListItem<T> {
	public Node(T value) {
		super(value);
	}

	@Override
	ListItem<T> next() {
		return rightLink;
	}

	@Override
	ListItem<T> setNext(ListItem<T> rightLink) {
		this.rightLink = rightLink;
		return next();
	}

	@Override
	ListItem<T> previous() {
		return leftLink;
	}

	@Override
	ListItem<T> setPrevious(ListItem<T> leftLink) {
		this.leftLink = leftLink;
		return previous();
	}

	@Override
	int compareTo(ListItem<T> item) {
		T value = getValue();
		T itemValue = item.getValue();

		return value.compareTo(itemValue);
	}
}