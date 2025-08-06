package polymorphismChallenge;

public class GasPoweredCar extends Car{
	
	private double avgKmPerLitre;
	private int cylinders;
	
	public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinders = cylinders;
	}
	
	public GasPoweredCar(double avgKmPerLitre, int cylinders) {
		super("Gas Powered");
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
	
	public void startEngine() {
		System.out.println("Gas Powered Car Starting Engine ");
	}
	
	public void drive() {
		startEngine();
		runEngine();
		System.out.println("Start driving Gas Powered Car");
	}
	
	private void runEngine() {
		System.out.println("Running Engine of Gas Powered Car");
	}

	@Override
	public String toString() {
		return "GasPoweredCar [avgKmPerLitre=" + avgKmPerLitre + ", cylinders=" + cylinders + "]";
	}
	
	
}
