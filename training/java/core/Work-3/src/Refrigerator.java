
public class Refrigerator {
	private boolean hasWorkToDo;
	
	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}

	public void orderFood() {
		if(this.hasWorkToDo)
			System.out.println("food is being ordered.");
			hasWorkToDo=false;
	}

}
