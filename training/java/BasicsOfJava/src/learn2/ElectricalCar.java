package learn2;

public class ElectricalCar extends Car{
	private double avgKmPerCharge;
	private int batterySize;
	
	public ElectricalCar(double avgKmPerCharge,int batterySize,String description) {
		super(description);
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}
	public double getAvgKmPerCharge() {
		return avgKmPerCharge;
	}
	public int getBatterySize() {
		return batterySize;
	}
}
