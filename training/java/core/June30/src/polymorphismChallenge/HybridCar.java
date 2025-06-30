package polymorphismChallenge;

public class HybridCar extends Car{

	private double avgKmPerLitre;
	private int batterySize;
	private int cylinders;
	
	public HybridCar(double avgKmPerLitre, int batterySize, int cylinders) {
		super("Electric Car");
		this.avgKmPerLitre = avgKmPerLitre;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}
	
	public HybridCar(String description, double avgKmPerLitre, int batterySize, int cylinders) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}

	
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}

	public void setAvgKmPerLitre(double avgKmPerLitre) {
		this.avgKmPerLitre = avgKmPerLitre;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public void startEngine() {
		System.out.println("Hybrid Car Starting Engine ");
	}
	
	public void drive() {
		startEngine();
		runEngine();
		System.out.println("Start driving Hybrid Car");
	}
	
	private void runEngine() {
		System.out.println("Running Engine of Hybrid Car");
	}
	
	@Override
	public String toString() {
		return "HybridCar [avgKmPerLitre=" + avgKmPerLitre + ", batterySize=" + batterySize + ", cylinders=" + cylinders
				+ "]";
	}
}
