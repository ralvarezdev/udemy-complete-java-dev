package oop.composition;

public class Refrigerator {
	private boolean hasWorkToDo;

	public Refrigerator() {
		this.hasWorkToDo = false;
	}

	public Refrigerator(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void orderFood() {
		if (hasWorkToDo)
			System.out.println("Ordering Food...");
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
}
