package corejava.june27_Inheritence;

public class Refrigerator {
	private boolean hasWorkToDo;

	public Refrigerator(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void orderFood() {
		if(this.isHasWorkToDo()) {
			System.out.println("Ordering Food...");
			this.hasWorkToDo=false;
		}
	}
}
