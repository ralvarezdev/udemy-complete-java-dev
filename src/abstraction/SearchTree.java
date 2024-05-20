package abstraction;

public class SearchTree<T extends Comparable<T>> implements NodeList<T> {
	private ListItem<T> root;

	public SearchTree(ListItem<T> root) {
		this.root = root;
	}

	@Override
	public ListItem<T> getRoot() {
		return root;
	}

	@Override
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

		else if (compareResult < 0)
			currItem.setNext(item);

		else
			currItem.setPrevious(item);

		return true;
	}

	@Override
	public boolean removeItem(ListItem<T> item) {
		ListItem<T> currItem, prevItem;
		boolean flag = false;
		int compareResult;

		prevItem = currItem = root;

		if (root == null || item == null)
			return false;

		if (root == item) {
			root = null;
			return true;
		}

		while (!flag) {
			compareResult = currItem.compareTo(item);

			if (compareResult == 0)
				performRemoval(item, prevItem);

			else if (compareResult < 0)
				flag = currItem.next() == null;

			else
				flag = currItem.previous() == null;

			if (flag)
				break;

			prevItem = currItem;

			if (compareResult == 0)
				return true;

			else if (compareResult < 0)
				currItem = currItem.next();

			else
				currItem = currItem.previous();
		}

		return false;
	}

	private void performRemoval(ListItem<T> item, ListItem<T> parentItem) {
		if (parentItem.next() == item)
			parentItem.setNext(null);

		else if (parentItem.previous() == item)
			parentItem.setPrevious(null);
	}

	@Override
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
