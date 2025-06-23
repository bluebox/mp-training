package learn2;

public class GasPoweredCar extends Car{
	private double avgKmPerLitre;
	private int cylinders;
	
	public GasPoweredCar(double avgKmPerLitre,String description) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
	}
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}
	public int getCylinders() {
		return cylinders;
	}
}
