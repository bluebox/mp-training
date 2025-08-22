
public class GasPoweredCar extends Car {
	private double avgKmPerLitre;
	private int cylinders;
	GasPoweredCar(String description,double avgKmPerLitre,int cylinders)
	{
		super(description);
		this.avgKmPerLitre=avgKmPerLitre;
		this.cylinders=cylinders;
		}
	public void startEngine()
	{
		System.out.println("Starts engine with "+cylinders+"cylinders");
	}
	public void Drive()
	{
		System.out.println("uses gas to drive");
	}
	protected void runEngine()
	{
		System.out.println("running on Electricity for "+avgKmPerLitre+"avgKmPerLitre");
	}

}

