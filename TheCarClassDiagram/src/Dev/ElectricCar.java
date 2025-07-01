package Dev;

public class ElectricCar extends Car {
	public ElectricCar(String description,double avgKmPerCharge) {
		super(description);
	}
	@Override
	public String startEngine() {
		return "e_car engine Started";
	}
	@Override
	protected String runEngine() {
		return "e-car is going...";
	}
	

}
