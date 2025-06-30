package Day4_30_06;

public class Car {
	private String description;
	public void startEngine() {
		System.out.println("Strated the Engine");
	}
	public void drive() {
		runEngine();
		System.out.println("Driving....");
		
	}
	protected void runEngine() {
		System.out.println("Engine is running");
	}
}
