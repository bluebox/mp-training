package June27;

public class Refrigerator {
	
	private boolean hasWorkToDo;
	
	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void orderFood() {
		if(isHasWorkToDo()) System.out.println("Ordering Food");
	}
}
