package learn2;

public class HybridCar extends Car{
	private int batterySize;
	private double avgKmPerLitre;
	private int cylinders;
	
	public HybridCar(double avgKmPerLitre,int batterySize,int cylinders,String description) {
		super(description);
		
		this.avgKmPerLitre = avgKmPerLitre;
		this.batterySize = batterySize;
		this.cylinders = cylinders;
	}
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}
	public int getCylinders() {
		return cylinders;
	}
	public int getBatterySize() {
		return batterySize;
	}
}
