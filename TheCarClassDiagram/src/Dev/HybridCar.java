package Dev;

public class HybridCar extends Car {
	public HybridCar(String description,int batterySize,int cylinders) {
		super(description);
	}
	@Override
	public String startEngine() {
		return "Hybrid Car engine started";
	}
	@Override
	protected String runEngine() {
		return "Hybrid car engine is running";
	}
	
	

}
