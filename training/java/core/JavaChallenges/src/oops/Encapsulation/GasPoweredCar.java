package oops.Encapsulation;

public class GasPoweredCar extends Car{
	
	
	private double avgKmPerLitre;
	private int cylinders;
	
	public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinders = cylinders;
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

	
	@Override
	public void startEngine()
	{
		System.out.println("Gas Powered car has started.");
	}
	
	@Override
	public void drive()
	{
		runEngine();
		System.out.println("Gas Powered car is driving");
	}
	
	@Override
	public void runEngine()
	{
		System.out.println("Gas Powered car is running");
	}

}