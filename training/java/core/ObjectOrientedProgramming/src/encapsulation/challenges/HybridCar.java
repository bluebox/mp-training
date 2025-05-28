package encapsulation.challenges;

public class HybridCar extends Car{
	private double avgKmPerLiter;
	private int batterySize;
	private int cylinders;
	
	public HybridCar(String description, double avgKmPerLiter, int batterySize, int cylinders) {
		super(description);
		this.avgKmPerLiter = avgKmPerLiter;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}

	public double getAvgKmPerLiter() {
		return avgKmPerLiter;
	}

	public void setAvgKmPerLiter(double avgKmPerLiter) {
		this.avgKmPerLiter = avgKmPerLiter;
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
	
	@Override
	public void startEngine()
	{
		System.out.println("Hybrid car Started.");
	}
	
	@Override
	public void drive()
	{
		runEngine();
		System.out.println("Hybrid car with "+getCylinders()+" and battery size"+getBatterySize()+" is being driven");
	}
	
	@Override
	public void runEngine()
	{
		System.out.println("Hybrid car Engine is running");
	}
}
