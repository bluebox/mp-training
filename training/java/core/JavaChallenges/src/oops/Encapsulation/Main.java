package oops.Encapsulation;



public class Main {
	
	public static void main(String[] args) {
		ElectricCar ele = new ElectricCar("Maruti",100, 100000);
		System.out.println("Class Name:"+ele.getClass().getSimpleName());
		ele.startEngine();
		ele.drive();
		System.out.println(ele.getAvgKmPerCharge());
		System.out.println(ele.getBatterySize());
		
		HybridCar hyb = new HybridCar("Maruti",2,100, 100000);
		System.out.println("Class Name:"+hyb.getClass().getSimpleName());
		hyb.startEngine();
		hyb.drive();
		System.out.println(hyb.getAvgKmPerLitre());
		System.out.println(hyb.getBatterySize());
		System.out.println(hyb.getCylinders());

		GasPoweredCar gc = new GasPoweredCar("Maruti",100, 2);
		System.out.println("Class Name:"+gc.getClass().getSimpleName());
		gc.startEngine();
		gc.drive();
		System.out.println(gc.getAvgKmPerLitre());
		System.out.println(gc.getCylinders());
	
	}

}