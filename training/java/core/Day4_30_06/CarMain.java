package Day4_30_06;

public class CarMain {
	public static void main(String args[]) {
		Car generalCar=new Car();
		System.out.println(generalCar.getClass().getName());
		generalCar.startEngine();
		generalCar.drive();
		
		System.out.println("_".repeat(20));
		ElectricalCar ecar=new ElectricalCar();
		System.out.println(ecar.getClass().getName());
		ecar.startEngine();
		ecar.drive();
		
		System.out.println("_".repeat(20));
		GasPoweredCar gcar=new GasPoweredCar();
		System.out.println(gcar.getClass().getName());
		gcar.startEngine();
		gcar.drive();
		
		System.out.println("_".repeat(20));
		HybridCar hcar=new HybridCar();
		System.out.println(hcar.getClass().getName());
		hcar.startEngine();
		hcar.drive();
	}
}
