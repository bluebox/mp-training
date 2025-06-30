package day4;

public class ElectricCar extends Car {
	private double avgKmPerCharge;
	private int batterySize;
	public ElectricCar(double avgKmPerCharge, int batterySize) {
		super();
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}

	public double getAvgKmPerCharge() {
		return avgKmPerCharge;
	}

	public int getBatterySize() {
		return batterySize;
	}

	@Override
	public void startEngine() {
		System.out.println("Electric Engine Started");
	}

	@Override
	public void drive() {
		System.out.println("Driving Electric Car");
	}

	@Override
	protected void runEngine() {
		System.out.println("Running Electric Car");
	}

}
