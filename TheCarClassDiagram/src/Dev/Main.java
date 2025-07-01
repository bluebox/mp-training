package Dev;

public class Main {

	public static void main(String[] args) {
		Car gasCar=new GasPoweredCar("jet xr",8);
		System.out.println(gasCar.startEngine());
		System.out.println(gasCar.drive());
		System.out.println(gasCar.runEngine());
		
		System.out.println("--------------");
		Car electricCar=new ElectricCar("tata nexon",10);
		System.out.println(electricCar.startEngine());
		System.out.println(electricCar.drive());
		System.out.println(electricCar.runEngine());
		
		System.out.println("--------------");
		Car hybridCar=new HybridCar("hybrid car",40,10);
		System.out.println(hybridCar.startEngine());
		System.out.println(hybridCar.drive());
		System.out.println(hybridCar.runEngine());
	}

}
