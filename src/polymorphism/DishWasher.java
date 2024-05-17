package polymorphism;

public class DishWasher {
	private boolean hasWorkToDo;

	public DishWasher() {
		this.hasWorkToDo = false;
	}

	public DishWasher(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void doDishes() {
		if (hasWorkToDo)
			System.out.println("Doing Dishes...");
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
}
