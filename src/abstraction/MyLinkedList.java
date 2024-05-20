package abstraction;

public class MyLinkedList<T extends Comparable<T>> implements NodeList<T> {
	private ListItem<T> root;

	public MyLinkedList(ListItem<T> root) {
		this.root = root;
	}

	public ListItem<T> getRoot() {
		return root;
	}

	public boolean addItem(ListItem<T> item) {
		ListItem<T> currItem = root;
		boolean flag = false;
		int compareResult = Integer.MIN_VALUE;

		if (item == null)
			return false;

		if (root == null) {
			root = item;
			return true;
		}

		while (!flag) {
			compareResult = currItem.compareTo(item);

			if (compareResult == 0)
				flag = true;

			else if (compareResult < 0) {
				flag = currItem.next() == null;
				if (!flag)
					currItem = currItem.next();
			}

			else {
				flag = currItem.previous() == null;
				if (!flag)
					currItem = currItem.previous();
			}
		}

		if (compareResult == 0)
			return false;

		if (compareResult < 0)
			currItem.setNext(item);

		else
			currItem.setPrevious(item);

		return true;
	}

	public boolean removeItem(ListItem<T> item) {
		ListItem<T> currItem, prevItem;
		boolean flag = false;
		int compareResult, prevResult;

		prevItem = currItem = root;
		compareResult = prevResult = Integer.MIN_VALUE;

		if (root == null || item == null)
			return false;

		if (root == item) {
			root = null;
			return true;
		}

		while (!flag) {
			compareResult = currItem.compareTo(item);

			if (compareResult < 0)
				flag = currItem.next() == null;

			else if (compareResult > 0)
				flag = currItem.previous() == null;

			else if (prevResult < 0)
				prevItem.setNext(null);

			else if (prevResult > 0)
				prevItem.setPrevious(null);

			if (flag)
				break;

			prevItem = currItem;
			prevResult = compareResult;

			if (compareResult == -1)
				currItem = currItem.next();

			else if (compareResult == 1)
				currItem = currItem.previous();

			else if (compareResult == 0)
				return true;
		}

		return false;

	}

	public void traverse(ListItem<T> item) {
		if (item == null) {
			System.out.println("The list is empty");
			return;
		}

		ListItem<T> previous, next;
		previous = item.previous();
		next = item.next();

		if (previous != null)
			traverse(previous);

		System.out.println(item.getValue());

		if (next != null)
			traverse(next);
	}
}