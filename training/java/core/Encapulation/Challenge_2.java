package Encapulation;

class Car {
	String description;

	public Car(String description) {
		System.out.println("Parent class constructor");

		this.description = description;
	}

	public void startEngine() {
		System.out.println("Parent Starting The Engine.");

	}

	public void drive() {
		System.out.println("Parent Starting The Engine.");
		// System.out.println("Starting The Engine.");

	}

	protected void runEngine() {

		System.out.println("Parent Starting The Engine.");
		// System.out.println("Starting The Engine.");

	}
}

class GasPoweredCar extends Car {
	double avgKmPerLitre;
	int cylinder;

	public GasPoweredCar(String description, double avgKmPerLitre, int cylinder) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinder = cylinder;

	}

	@Override
	public void startEngine() {
		System.out.println("Overridind parentclass function");

		System.out.println("We are on Gas powered car ;Starting The Engine.");

	}

	@Override
	public void drive() {
		System.out.println("Overridind parentclass function");

		System.out.println("We are driving gas powered car.");
		// System.out.println("Starting The Engine.");

	}

	@Override
	protected void runEngine() {
		System.out.println("Overridind parentclass function");

		System.out.println("We are running our gas powered engine.");
//		System.out.println("Starting The Engine.");

	}

}

class ElectricCar extends Car {
	double avgkmPerCharge;
	int batterySize;

	public ElectricCar(String description, double avgkmPerCharge, int batterySize) {
		super(description);
		this.avgkmPerCharge = avgkmPerCharge;
		this.batterySize = batterySize;
	}

	@Override
	public void startEngine() {
		System.out.println("Overridind parentclass function");

		System.out.println("Starting Electric Engine.");

	}

	@Override
	public void drive() {
		System.out.println("Overridind parentclass function");

		System.out.println("Driving Electric Engine.");
		// System.out.println("Starting The Engine.");

	}

	@Override
	protected void runEngine() {
		System.out.println("Overridind parentclass function");

		System.out.println("Running on electric car The Engine.");
		// System.out.println("Starting The Engine.");

	}
}

class HybridCar extends Car {
	double avgKmPerLitre;
	int batterySize;
	int cylinder;

	public HybridCar(String description, double avgkmPerLitre, int batterySize, int cylinder) {
		super(description);
		this.avgKmPerLitre = avgkmPerLitre;
		this.batterySize = batterySize;
		this.cylinder = cylinder;
	}

	@Override
	public void startEngine() {
		System.out.println("Overridind parentclass function");

		System.out.println("Its Hybrid car;Starting The Engine.");

	}

	@Override
	public void drive() {
		System.out.println("Overridind parentclass function");

		System.out.println("Starting The Hybrid car Engine.");
		// System.out.println("Starting The Engine.");

	}

	@Override
	protected void runEngine() {
		System.out.println("Overridind parentclass function");
		System.out.println("Hybrid car engine running.");
		// System.out.println("Starting The Engine.");

	}

}

public class Challenge_2 {
	public static void main(String[] args) {
		Car tata = new Car("My New Car");
		Car punchEV = new ElectricCar("PunchEV", 230.5, 35);
		Car gasCar = new GasPoweredCar("Its Gas Powered Car.", 55.0, 6);
		Car hybridCar = new HybridCar("Its new Hybrid Vehicle.", 40.35, 30, 4);
		System.out.println();
		tata.drive();
		System.out.println();
		gasCar.drive();
		System.out.println();

		punchEV.drive();
		System.out.println();

		hybridCar.drive();
		System.out.println();

		tata.runEngine();
		System.out.println();

		gasCar.runEngine();
		System.out.println();

		punchEV.runEngine();
		System.out.println();

		hybridCar.runEngine();
		System.out.println();

	}

}
