package June27;

public class CoffeeMaker {
	
	private boolean hasWorkToDo;
	
	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void brewCoffee() {
		if(isHasWorkToDo()) System.out.println("Coffee Brewing");
	}
}
