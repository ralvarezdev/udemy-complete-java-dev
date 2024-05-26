package generics;

import java.util.LinkedList;
import java.util.List;

public class Layer<T extends Mappable> {
	private LinkedList<T> elements;

	public static void main(String[] args) {
		Layer<Mappable> layer = new Layer<>();

		layer.addElement(new Park("Vereda", -10, 10));
		layer.addElement(new Park("Ciudad Ojeda", "-20,20"));
		layer.addElement(new River("Catatumbo", new Point(10, 20), new Point(30, 40)));
		layer.addElement(new River("Amazonas", "0,11", "15,28", "84, 15", "78,33"));
		layer.renderLayer();
	}

	public Layer() {
		this.elements = new LinkedList<>();
	}

	public Layer(List<T> elements) {
		this();
		addElements(elements);
	}

	public boolean addElement(T element) {
		if (element == null)
			return false;

		elements.add(element);
		return true;
	}

	public boolean addElements(List<T> elements) {
		if (elements == null)
			return false;

		for (T element : elements)
			addElement(element);

		return true;
	}

	public void renderLayer() {
		System.out.println("Layer elements:");
		System.out.println();
		boolean isFirst = true;

		for (T element : elements) {
			if (!isFirst)
				System.out.println("-".repeat(10));
			else
				isFirst = false;

			element.render();
		}
	}
}
