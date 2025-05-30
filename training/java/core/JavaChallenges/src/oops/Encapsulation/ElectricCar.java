package oops.Encapsulation;


public class ElectricCar extends Car{
	private double avgKmPerCharge;
	private int batterySize;
	public ElectricCar(String description,double avgKmPerCharge, int batterySize) {
		super(description);
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
	
	@Override
	public void startEngine()
	{
		System.out.println("Electric Car Engine has Started");
	}
	
	@Override
	public void drive()
	{
		runEngine();
		System.out.println("Electric car is driving");
	}
	
	
	@Override
	public void runEngine()
	{
		System.out.println("Electric Car is running");
	}
}