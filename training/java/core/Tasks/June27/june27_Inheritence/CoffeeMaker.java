package corejava.june27_Inheritence;

public class CoffeeMaker {
	private boolean hasWorkToDo;

	public CoffeeMaker(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void brewCoffee() {
		if(this.isHasWorkToDo()) {
			System.out.println("Brewing coffee...");
			this.hasWorkToDo=false;
		}
	}
}
