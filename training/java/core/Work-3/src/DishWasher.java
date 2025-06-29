
public class DishWasher {
	private boolean hasWorkToDo;

	
	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}


	public void doDishes() {
		if(this.hasWorkToDo) {
			System.out.println("Cleaning the Dishes");
			hasWorkToDo=false;
		}
	}
}
