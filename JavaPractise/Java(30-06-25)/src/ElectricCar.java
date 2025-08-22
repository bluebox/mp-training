public class ElectricCar extends Car{
	private double avgKmPerLitre;
	private int batterySize;
	ElectricCar(String description,double avgKmPerLitre,int batterySize)
	{
		super(description);
		this.avgKmPerLitre=avgKmPerLitre;
		this.batterySize=batterySize;
		}
	public void startEngine()
	{
		System.out.println("Starts engine with "+batterySize+"batterySize");
	}
	public void Drive()
	{
		System.out.println("uses power to drive");
	}
	protected void runEngine()
	{
		System.out.println("running on Electricity for "+avgKmPerLitre+"avgKmPerLitre");
	}
}

