package corejava.june30_CarClass;

public class Car {
	private String description;

	
	public Car(String description) {
		super();
		this.description = description;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean startEngine() {
		System.out.println("Engine Started...");
		return true;
	}
	
	protected void runEngine(Boolean check) {
		if (check) {
	        System.out.println("Engine is running smoothly...");
	    } 
	}
	
	public void drive() {
		if(startEngine()) {
			runEngine(startEngine());
			System.out.println("Driving the car...");
		}
		else {
			System.out.println("Unable to drive!!!");
		}
		
	}
	
}
