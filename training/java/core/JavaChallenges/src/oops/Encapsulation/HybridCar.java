package oops.Encapsulation;

public class HybridCar extends Car{
	
	
	private double avgKmPerLitre;
	private int cylinders;
	private int batterySize;
	
	public HybridCar(String description, double avgKmPerLitre, int cylinders,int batterySize) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinders = cylinders;
		this.batterySize=batterySize;
	}
	
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}

	public void setAvgKmPerLitre(double avgKmPerLitre) {
		this.avgKmPerLitre = avgKmPerLitre;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
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
		System.out.println("Hybrid Car has started.");
	}
	
	@Override
	public void drive()
	{
		runEngine();
		System.out.println("Hybrid Car is driving");
	}
	
	@Override
	public void runEngine()
	{
		System.out.println("Hybrid Car is running");
	}

}