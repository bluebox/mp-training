package inheritance.medplus;

public class Car {
	
	public static void main(String[] args) {
		CarParent cp = new CarParent();
		GasPoweredCar Gpc = new GasPoweredCar();
		ElectricCar ec = new ElectricCar();
		HybridCar hc = new HybridCar();
		
		
		cp.startEngine();
		cp.drive();
		cp.runEngine();
		
		System.out.println();
		
		Gpc.startEngine();
		Gpc.drive();
		Gpc.runEngine();
		
		System.out.println();
		
		ec.startEngine();
		ec.drive();
		ec.runEngine();
		
		System.out.println();
		
		hc.startEngine();
		hc.drive();
		hc.runEngine();
	}

}
class CarParent{
	private String description;
	
	public void startEngine() {
		System.out.println("The engine started !!!");
		System.out.println("In the class : "+this.getClass().getName());
	}
	
	public void drive() {
		System.out.println("Its a foue wheel drive .");
	}
	protected void runEngine() {
		System.out.println("The Engine is running .");
	}
}
class GasPoweredCar extends CarParent {
	private double avgKmPerLitre;
	private int cylinder;
	
	public void startEngine() {
		System.out.println("gas flowed -> Engine Started ");
		System.out.println("In the class : "+this.getClass().getName());
	}
	
}
class ElectricCar extends CarParent{
	private double avgKmPerCharge;
	private int batterySize;
	
	
	public void startEngine() {
		System.out.println("Battery connect -> Engine Started ");
		System.out.println("In the class : "+this.getClass().getName());
	}
	
}

class HybridCar extends CarParent{
	private double avgKmPerLitre;
	private int batterySize;
	private int cylinders;
	
	public void startEngine() {
		System.out.println("gas or  battery together -> started engine !!!");
		System.out.println("In the class : "+this.getClass().getName());
	}
}
