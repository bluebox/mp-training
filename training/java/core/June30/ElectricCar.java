package June30;

public class ElectricCar extends Car {
	private double avgKmPerCharge;
	private int batterySize;
	
	public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
		super(description);
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}
	
	@Override
	public void startEngine()
	{
		System.out.println("Starts engine with "+batterySize+" batterySize");
	}
	
	@Override
	public void drive()
	{
		System.out.println("Uses power to drive");
	}
	
	@Override
	protected void runEngine()
	{
		System.out.println("Running on Electricity for "+avgKmPerCharge+" avgKmPerLitre");
	}	
}
