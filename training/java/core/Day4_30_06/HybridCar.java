package Day4_30_06;

public class HybridCar extends Car{
	double avgKmPerlitre;
	int batterySize;
	int cylinders;
	
	@Override
	public void startEngine() {
		System.out.println("Stsrting the Hybrid Engine");
	}
	@Override
	public void drive() {
		this.runEngine();
		System.out.println("It can give a mileage of "+this.avgKmPerlitre+" per litre");
	}
	@Override
	protected void runEngine() {
		System.out.println("Running the "+this.cylinders+" engine");
		System.out.println("The battery size is "+this.batterySize);
	}
	
}
