package composition;

public class CoffeeMaker {
	
	private boolean hasWorkToDo;

	public CoffeeMaker()
	{
		
	}
	public CoffeeMaker(boolean hasWorkToDo) {
		super();
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void brewCoffee()
	{
		if(hasWorkToDo)
		System.out.println("Brewing Coffee");
		hasWorkToDo = false;
	}

}
