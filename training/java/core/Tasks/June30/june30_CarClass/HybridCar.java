package corejava.june30_CarClass;

public class HybridCar extends Car{
	private double avgKmPerLiter;
	private int batterySize;
	private int cylinders;
	
	public HybridCar(double avgKmPerLiter, int batterySize, int cylinders,String description) {
		super(description);
		this.avgKmPerLiter = avgKmPerLiter;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}

	public double getAvgKmPerLiter() {
		return avgKmPerLiter;
	}

	public void setAvgKmPerLiter(double avgKmPerLiter) {
		this.avgKmPerLiter = avgKmPerLiter;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	
	@Override
	public boolean startEngine() {
		if(this.avgKmPerLiter>=100 && this.cylinders>=3) {
			return true;
		}
		else if( this.batterySize>=100) {
			return true;
		}
	    return false;
	}
	
	@Override
	public void drive() {
		if(startEngine()) {
			runEngine(startEngine());
			System.out.println("Driving the HybridCar...");
		}
		else {
			System.out.println("Engine is not started. Please start the engine first.");
			System.out.println("Unable to drive!!!");
		}
		
	}
	
	@Override
	public String toString() {
	    return this.getClass().getSimpleName()+": [ Description= "+this.getDescription()+", AvgKmPerLiter=" + this.avgKmPerLiter +", Cylinders=" + this.cylinders + ", BatterySize= "+ this.batterySize+"]";
	}

	public void printCarInfo() {
	    System.out.println(this.toString());
	}

	
}
