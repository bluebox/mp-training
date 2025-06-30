package corejava.june27_Inheritence;

public class DishWasher {
	private boolean hasWorkToDo;

	public DishWasher(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void doDishes() {
		if(this.isHasWorkToDo()) {
			System.out.println("Preparing Dishes...");
			this.hasWorkToDo=false;
		}
	}
}
