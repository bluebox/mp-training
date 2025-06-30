package day4;

public class ElectricCar extends Car {

	private double avgKmPerLiter;
	private int batterySize;
	private String description;
	
	public ElectricCar() {
		this.avgKmPerLiter=150;
		this.batterySize=350;
		this.description="Car Works under electricity and gives an average of "+avgKmPerLiter+"kms and is powered by "+batterySize+" kmwhs Battery ";

	}
	

	@Override
	public void startEngine() {
		// TODO Auto-generated method stub
		System.out.println("Car engine is started and electricity is used.");

	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("These cars are all wheel drive ");
	}
	 
	protected void runEngine() {
		// TODO Auto-generated method stub
		System.out.println(description);
	}
	


}
