package composition;

public class DishWasher {
	
	private boolean hasWorkToDo;

	public DishWasher(boolean hasWorkToDo) {
		super();
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void doDishes()
	{
		System.out.println("Doing Dishes");
	}

}
