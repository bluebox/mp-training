package learn2;

public class Car {
	
	private String description;

	public String getDescription() {
		return description;
	}

	public Car(String description) {
		this.description = description;
	}
	
	public void startEngine() {
		System.out.println("The Engine Started ");
	}
	
	protected void drive() {
		System.out.println("Started Driving");
		runEngine();
	}
	
	public void runEngine() {
		
		System.out.println("engine running");
	}
}
