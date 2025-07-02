package Polymorphism;

public class Main {

	public static void main(String[] args) {
		
		
		Car electricCar=new ElectricCar();
		
		Car GasCar=new GasPoweredCar();
		
		Car HybridCar=new HybridCar();
		
		electricCar.startEngine();
		electricCar.drive();
		GasCar.drive();
		GasCar.startEngine();
		HybridCar.startEngine();
		HybridCar.drive();
		System.out.println(electricCar);

	}

}
