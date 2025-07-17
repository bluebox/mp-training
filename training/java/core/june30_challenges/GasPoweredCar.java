package june30_collections;

public class GasPoweredCar extends Car{
	private double avgKmPerLitre;
	private int cylinders;
	
	GasPoweredCar(String des,double avgKmPerLitre,int cylinders){
		super(des);
		this.avgKmPerLitre=avgKmPerLitre;
		this.cylinders=cylinders;
	}
	
	
	@Override
	public void startEngine() {
		System.out.println(description+" has started with "+cylinders+" cylinders");
	}
	
	@Override
	public void drive() {
		System.out.println(description+" is now driving");
		runEngine();
	}
	
	@Override
	public void runEngine() {
		System.out.println(description+" engine is Running with fuel efficiency of "+avgKmPerLitre+" km/l.");
	}
}
