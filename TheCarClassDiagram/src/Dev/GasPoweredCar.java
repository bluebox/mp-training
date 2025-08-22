package Dev;

public class GasPoweredCar extends Car {
	private int cylinders;
	public GasPoweredCar(String description,int cylinders) {
		super(description);
		this.cylinders=cylinders;
	}
	@Override
	public String startEngine() {
		return "GasPowered car--all "+cylinders+" are fired";
	}
	@Override
	protected String runEngine() {
		return "Gas Powered Car->running on gasoline";
	}
	
}
