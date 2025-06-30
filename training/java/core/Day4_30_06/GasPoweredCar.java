package Day4_30_06;

public class GasPoweredCar extends Car{
	double avgKmPerLitre;
	int Cylinders;
	@Override
	public void startEngine() {
		System.out.println("Started the Gas Engine with "+ this.Cylinders);
	}
	@Override
	public void drive() {
		this.runEngine();
		System.out.println("Driving the Gas Car...");
		System.out.println("It can give a mileage of "+this.avgKmPerLitre+" per litre");
	}
	@Override
	protected void runEngine() {
		System.out.println("Running the Mega Gas engine");
	}
	
}
