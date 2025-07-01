package corejava.june30_CarClass;

public class GasPoweredCar extends Car {
	private double avgKmPerLiter;
	private int cylinders;
	
	
	public GasPoweredCar(double avgKmPerLiter, int cylinders, String description) {
		super(description);
		this.avgKmPerLiter = avgKmPerLiter;
		this.cylinders = cylinders;
	}
	
	
	public double getAvgKmPerLiter() {
		return avgKmPerLiter;
	}
	public void setAvgKmPerLiter(double avgKmPerLiter) {
		this.avgKmPerLiter = avgKmPerLiter;
	}
	public int getCylinders() {
		return cylinders;
	}
	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	
	@Override
	public boolean startEngine() {
		if(this.avgKmPerLiter>=12 && this.cylinders>=3) {
			return true;
		}
	    return false;
	}
	
	@Override
	public void drive() {
		if(startEngine()) {
			runEngine(startEngine());
			System.out.println("Driving the GasPoweredCar...");
		}
		else {
			System.out.println("Engine is not started. Please start the engine first.");
			System.out.println("Unable to drive!!!");
		}
		
	}

	@Override
	public String toString() {
	    return this.getClass().getSimpleName()+": [ Description= "+this.getDescription()+", AvgKmPerLiter=" + this.avgKmPerLiter +", Cylinders=" + this.cylinders + "]";
	}

	public void printCarInfo() {
	    System.out.println(this.toString());
	}
	
}
