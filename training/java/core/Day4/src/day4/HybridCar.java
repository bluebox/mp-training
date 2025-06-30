package day4;

public class HybridCar extends Car{
	private double avgKmPerLiter;
	private int batterySize;
	private int cylinders;
	private String description;
	 
	public HybridCar() {
		this.avgKmPerLiter=170.5;
		this.batterySize=250;
		this.cylinders=4;
		this.description="Car Works under both gas and electricity and  gives an average of "+avgKmPerLiter+"kms and is powered by "+cylinders+" "
				+ "cylinders and has a battery of "+batterySize+" kwmhs.";
	}

	@Override
	public void startEngine() {
		// TODO Auto-generated method stub
		super.startEngine();
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		super.drive();
	}

	@Override
	protected void runEngine() {
		// TODO Auto-generated method stub
		System.out.println(description);
	}
	
	
	

}
