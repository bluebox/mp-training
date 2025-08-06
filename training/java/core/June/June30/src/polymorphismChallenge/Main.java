package polymorphismChallenge;

public class Main {

	public static void main(String[] args) {
		
		Car car = new Car("car");
		car.toString();
		car.drive();
		System.out.println();
		
		Car electricCar = new ElectricCar("elecric Car", 50, 5000);
		electricCar.toString();
		electricCar.drive();
		System.out.println();
		
		Car gasCar = new GasPoweredCar("Gas Car", 60, 50);
		gasCar.toString();
		gasCar.drive();
		System.out.println();
		
		Car hybridCar = new HybridCar("elecric Car", 55, 5000,20);
		hybridCar.toString();
		hybridCar.drive();
		
	}

}
