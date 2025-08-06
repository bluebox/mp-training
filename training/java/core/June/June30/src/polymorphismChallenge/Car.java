package polymorphismChallenge;

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

	public void startEngine() {
		System.out.println("Starting Engine of Car");
	}
	
	public void drive() {
		startEngine();
		runEngine();
		System.out.println("Start driving " +description);
	}
	
	private void runEngine() {
		System.out.println("Running Engine of Car");
	}

	@Override
	public String toString() {
		return "Car [description=" + description + "]";
	}
	
}
