package smartKitchen;

public class Refrigerator {
	boolean hasWorkToDo;
	
	public Refrigerator(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}
	
	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void orderFood() {
		if(hasWorkToDo) {
			System.out.println("Ordered Food");
			setHasWorkToDo(false);
		}
		else {
			System.out.println("cannot order now");
		}
	}
	
}
