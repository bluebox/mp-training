package June27;

public class DishWasher {

	private boolean hasWorkToDo;
	
	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void doDishes() {
		if(isHasWorkToDo()) System.out.println("Doing dishes");
	}
}
