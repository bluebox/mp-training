package corejava.june30_CarClass;

public class ElectricCar extends Car {
	private double avgKmPerCharge;
	private int batterySize;
	
	public ElectricCar(double avgKmPerCharge, int batterySize,String description) {
		super(description);
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}

	public double getAvgKmPerCharge() {
		return avgKmPerCharge;
	}

	public void setAvgKmPerCharge(double avgKmPerCharge) {
		this.avgKmPerCharge = avgKmPerCharge;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}
	
	@Override
	public boolean startEngine() {
		if(this.avgKmPerCharge>=100 && this.batterySize>=30) {
			return true;
		}
		return false;
	}
	
	@Override
	public void drive() {
		if(startEngine()) {
			runEngine(startEngine());
			System.out.println("Driving the ElectricCar...");
		}
		else {
			System.out.println("Engine is not started. Please start the engine first.");
			System.out.println("Unable to drive!!!");
		}
		
	}
	

	@Override
	public String toString() {
	    return this.getClass().getSimpleName()+": [ Description= "+this.getDescription()+",AvgKmPerLiter=" + this.avgKmPerCharge +",  BatterySize= "+ this.batterySize+"]";
	}

	public void printCarInfo() {
	    System.out.println(this.toString());
	}
}
