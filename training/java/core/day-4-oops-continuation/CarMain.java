package day4;

public class CarMain {

	public static void main(String[] args) {
		
		ElectricCar electricCar = new ElectricCar(457.8,4);
		System.out.println("Electric Car created");
		electricCar.startEngine();
		electricCar.runEngine();
		electricCar.drive();
		System.out.println("Average Km per charge is " + electricCar.getAvgKmPerCharge());
		System.out.println("Number of batteries are "+electricCar.getBatterySize());
		System.out.println("-------------------------------------");
		
		GasPoweredCar gasCar = new GasPoweredCar(323.4,3);
		System.out.println("GasPowered Car created");
		gasCar.startEngine();
		gasCar.runEngine();
		gasCar.drive();
		System.out.println("Average Km per litre is " + gasCar.getAvgKmPerLitre());
		System.out.println("Number of cylinders are "+gasCar.getCylinders());
		System.out.println("-------------------------------------");
		
		HybridCar hyrbidCar = new HybridCar(549.2,3,4);
		System.out.println("HybridCar created");
		hyrbidCar.startEngine();
		hyrbidCar.runEngine();
		hyrbidCar.drive();
		System.out.println("Average Km per litre is " + hyrbidCar.getAvgKmPerLitre());
		System.out.println("Number of batteries are "+hyrbidCar.getBatterySize());
		System.out.println("Number of cylinders are "+hyrbidCar.getCylinders());
		System.out.println("-------------------------------------");
		
	}

}
