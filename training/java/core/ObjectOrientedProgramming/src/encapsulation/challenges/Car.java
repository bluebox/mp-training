package encapsulation.challenges;

public class Car {
	private String description;
	
	public Car(String description) {
		super();
		this.description = description;
	}
	
	public Car(){}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void startEngine()
	{
		System.out.println("Engine Started.");
	}
	public void drive()
	{
		System.out.println("Driving.");
	}
	
	protected void runEngine()
	{
		System.out.println("Engine Running.");
	}
}
