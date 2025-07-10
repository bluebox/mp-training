package June30;

public class Car {
	private String description;
	
	public Car(String description) {
		this.description = description;
	}

	public void startEngine() {
		System.out.println(description + "Car has different types to start engine");
	}
	
	public void drive() {
		System.out.println(description + "Car has different types of cars to drive");
	}
	
	protected void runEngine() {
		System.out.println(description + "Car has different types to run engine");
	}
}
