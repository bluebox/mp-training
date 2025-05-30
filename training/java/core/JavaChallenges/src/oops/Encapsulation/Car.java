package oops.Encapsulation;

public class Car {
	private String description;
	
	public Car(String description) {

		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void startEngine()
	{
		System.out.println("Engine has Started.");
	}
	public void drive()
	{
		System.out.println("Driving the Car.");
	}
	
	protected void runEngine()
	{
		System.out.println("Engine is Running.");
	}
}