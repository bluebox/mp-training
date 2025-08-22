
public class HybridCar extends Car {
	private double avgKmPerLitre;
	private int cylinders;
	private int batterySize;
	HybridCar(String description,double avgKmPerLitre,int cylinders,int batterySize)
	{
		super(description);
		this.avgKmPerLitre=avgKmPerLitre;
		this.cylinders=cylinders;
		this.batterySize=batterySize;
		}
	public void startEngine()
	{
		System.out.println("Starts engine with "+cylinders+"cylinders and also with "+batterySize+"batterySize");
	}
	public void Drive()
	{
		System.out.println("Driving with gas and power");
	}
	protected void runEngine()
	{
		System.out.println("running on Electricity for "+avgKmPerLitre+"avgKmPerLitre");
	}

}


