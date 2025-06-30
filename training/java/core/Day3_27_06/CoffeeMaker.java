package Day3_27_06;

public class CoffeeMaker {
	private boolean hasWorkToDo;
	
	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void brewCoffee() {
		if(this.isHasWorkToDo()) {
			System.out.println("Make Some Coffee...");
		}
	}
}
