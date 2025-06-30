package June30;

public class HybridCar extends Car {
	private double avgKmPerLitre;
	private int batterySize;
	private int cylinders;
	
	public HybridCar(String description, double avgKmPerLitre, int batterySize, int cylinders) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}
	
	@Override
	public void startEngine()
	{
		System.out.println("Starts engine with "+cylinders+"cylinders and also with "+batterySize+" batterySize");
	}
	
	@Override
	public void drive()
	{
		System.out.println("Driving with gas and power");
	}
	
	@Override
	protected void runEngine()
	{
		System.out.println("Running on Electricity for "+avgKmPerLitre+" avgKmPerLitre");
	}
}
