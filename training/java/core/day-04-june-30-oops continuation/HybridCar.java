package day4;

public class HybridCar extends Car {
	
	private double avgKmPerLitre;
	private int batterySize;
	private int cylinders;
	
	public HybridCar(double avgKmPerLitre, int batterySize, int cylinders) {
		super();
		this.avgKmPerLitre = avgKmPerLitre;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}

	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public int getCylinders() {
		return cylinders;
	}

	@Override
	public void startEngine() {
		System.out.println("Hybrid Engine Started");
	}

	@Override
	public void drive() {
		System.out.println("Driving Hybrid Car");
	}

	@Override
	protected void runEngine() {
		System.out.println("Running Hybrid Car");
	}

}
