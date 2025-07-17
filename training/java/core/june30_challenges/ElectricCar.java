package june30_collections;

public class ElectricCar extends Car{
	private double avgKmPerCharge;
	private int batterySize;
	
	ElectricCar(String des,double avgKmPerCharge,int batterySize){
		super(des);
		this.avgKmPerCharge=avgKmPerCharge;
		this.batterySize=batterySize;
	}
	
	@Override
	public void startEngine() {
		System.out.println(description+" has started with "+batterySize+" Kwh battery");
	}
	
	@Override
	public void drive() {
		System.out.println(description+" is now driving");
		runEngine();
	}
	
	@Override
	public void runEngine() {
		System.out.println(description+" engine is Running with "+avgKmPerCharge+" km per Charge.");
	}
}
