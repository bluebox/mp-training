package composition;

public class DishWasher {
	private boolean hasWorkToDo = false;
	public void doDishes() {
		System.out.println("Washing dishes....");
		hasWorkToDo = true;
	}
	public boolean getStatus() {
	return hasWorkToDo;
	}
	public void setStatus(boolean status) {
		 hasWorkToDo = status;
		}

}
