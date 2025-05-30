package composition;

public class CoffeeMaker {
	private boolean hasWorkToDo = false;
	public void brewCoffee() {
		System.out.println("Brewing coffee....");
		hasWorkToDo = true;
	}
	public boolean getStatus() {
	return hasWorkToDo;
	}
	public void setStatus(boolean status) {
		 hasWorkToDo = status;
		}
}
