package composition;

public class Refrigerator {
	private boolean hasWorkToDo;

	public Refrigerator()
	{
		
	}
	public Refrigerator(boolean hasWorkToDo) {
		super();
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void orderFood()
	{
		if(hasWorkToDo)
			System.out.println("Food Ordered");
		hasWorkToDo = false;
	}
	
}
