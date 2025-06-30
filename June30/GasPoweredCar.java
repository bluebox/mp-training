package June30;

public class GasPoweredCar extends Car{
	private double avgKmPerLitre;
	private int cylinders;
	
	public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinders = cylinders;
	}

	@Override
	public void startEngine()
	{
		System.out.println("Starts engine with "+cylinders+" cylinders");
	}
	
	@Override
	public void drive()
	{
		System.out.println("Uses gas to drive");
	}
	
	@Override
	protected void runEngine()
	{
		System.out.println("Running on Electricity for "+avgKmPerLitre+" avgKmPerLitre");
	}
	
		
}
