package composition;

public class Refridgerator {
	private boolean hasWorkToDo = false;
	public void orderFood() {
		System.out.println("Ordering food....");
		hasWorkToDo = true;
	}
	public boolean getStatus() {
	return hasWorkToDo;
	}
	public void setStatus(boolean status) {
		 hasWorkToDo = status;
		}

}
