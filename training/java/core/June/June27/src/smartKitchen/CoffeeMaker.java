package smartKitchen;

public class CoffeeMaker {
	boolean hasWorkToDo;
	
	

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
		if(hasWorkToDo) {
			System.out.println("Coffee Brewing done");
			setHasWorkToDo(false);
		}
		else {
			System.out.println("cannot brew now");
		}
	}
}
