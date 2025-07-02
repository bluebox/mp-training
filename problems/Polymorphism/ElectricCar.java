package Polymorphism;

public class ElectricCar extends Car{
     private double avgKmPerCharge;
     private int batterySize;
     
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
	
	public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
		super(description);
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}
	
	public ElectricCar() {
		
	}
	
	@Override
	public void startEngine() {
		   System.out.println("The Engine of the Electric Car is Running with "+avgKmPerCharge+"Km per Charge.");
	   }
	@Override
	   public void drive() {
		   System.out.println("The Electric Car has Started");
		   runEngine();
	   }
	@Override
	   protected void runEngine() {
		   System.out.println("The Electric Car Engine is running");
	   }
	  
	  
     
	
     
     
     
     
}
