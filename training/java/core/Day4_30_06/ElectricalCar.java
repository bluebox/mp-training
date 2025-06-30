package Day4_30_06;

public class ElectricalCar extends Car{
	double avgKmPerCharge;
	int batterySize;
	@Override
	public void startEngine() {
		System.out.println("Strated the MOTOR electic engine with the battery size "+ batterySize);
	}
	@Override
	public void drive() {
		this.runEngine();
		System.out.println("Driving the Electic car");
		System.out.println("We can drive this car for "+this.avgKmPerCharge+" on full charge");
	}
	@Override
	protected void runEngine() {
		System.out.println("Running the electric engine");
	}
	
}
