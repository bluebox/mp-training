package smartKitchen;

public class DishWasher {
	boolean hasWorkToDo;
	

	public DishWasher(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}

	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void doDishes() {
		if(hasWorkToDo) {
			System.out.println("Dishes are done");
			setHasWorkToDo(false);
		}
		else {
			System.out.println("cannot do dishes now");
		}
	}
}
