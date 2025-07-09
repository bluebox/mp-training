package day4;

public class GasPoweredCar extends Car {
	
	private double avgKmPerLitre;
	private int cylinders;
	
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}

	public int getCylinders() {
		return cylinders;
	}

	public GasPoweredCar(double avgKmPerLitre, int cylinders) {
		super();
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinders = cylinders;
	}

	@Override
	public void startEngine() {
		System.out.println("Gas Engine Started");
	}

	@Override
	public void drive() {
		System.out.println("Driving Gas Car");
	}

	@Override
	protected void runEngine() {
		System.out.println("Running Gas Car");
	}

}
