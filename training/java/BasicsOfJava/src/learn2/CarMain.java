package learn2;

public class CarMain {
	
	public static void main(String[] args) {
		
		GasPoweredCar gasCar = new GasPoweredCar(40.25,"Nano");
		ElectricalCar electricCar = new ElectricalCar(80.25, 50 ,"Ola");
		HybridCar hybridCar = new HybridCar(35.55,30, 2,"New");
		
		System.out.println(gasCar.getClass().getSimpleName());
		gasCar.startEngine();
		gasCar.drive();
		
		System.out.println(electricCar.getClass().getSimpleName());
		electricCar.startEngine();
		electricCar.drive();
		
		System.out.println(hybridCar.getClass().getSimpleName());
		hybridCar.startEngine();
		hybridCar.drive();
		
	}
}
