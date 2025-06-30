package polymorphismChallenge;

public class ElectricCar extends Car {
	
	private double avgKmPerCharge;
	private int batterySize;
	
	public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
		super(description);
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}
	
	public ElectricCar(double avgKmPerCharge, int batterySize) {
		super("Electric Car");
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}

	public double getAvgKmPerCharge() {
		return avgKmPerCharge;
	}

	public void setAvgKmPerCharge(double avgKmPerCharge) {
		this.avgKmPerCharge = avgKmPerCharge;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public void startEngine() {
		System.out.println("Electric Car Starting Engine ");
	}
	
	public void drive() {
		startEngine();
		runEngine();
		System.out.println("Start driving Electric Car");
	}
	
	private void runEngine() {
		System.out.println("Running Engine of Electric Car");
	}
	
	@Override
	public String toString() {
		return "ElectricCar [avgKmPerCharge=" + avgKmPerCharge + ", batterySize=" + batterySize + "]";
	}
	
}
