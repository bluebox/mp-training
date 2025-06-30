package day4;

public class GasPoweredCar extends Car{
	
	private double avgKmPerLiter;
	private int cylinders;
	private String description;

	
	public GasPoweredCar() {
		this.avgKmPerLiter=20.5;
		this.cylinders=8;
		this.description="Car Works under gas and gives an average of "+avgKmPerLiter+"kms and is powered by "+cylinders+" cylinders";
	}
	 

	@Override
	public void startEngine() {
		// TODO Auto-generated method stub
		System.out.println("Car engine is started and fuel is pumped");
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("These cars are either rear wheel drive or all wheel drive ");
	}
	 
	protected void runEngine() {
		// TODO Auto-generated method stub
		System.out.println(description);
	}
	
	
}
