package Day3_27_06;

public class Refregirator {
	private boolean hasWorkToDo;
	public boolean isHasWorkToDo() {
		return hasWorkToDo;
	}
	
	
	public void setHasWorkToDo(boolean hasWorkToDo) {
		this.hasWorkToDo = hasWorkToDo;
	}


	public void orderFood() {
		if(this.isHasWorkToDo()) {
			System.out.println("Cooling...");
		}
	}
}
