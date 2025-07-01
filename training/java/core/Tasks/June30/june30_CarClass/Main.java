package corejava.june30_CarClass;

public class Main {

	public static void main(String[] args) {
		HybridCar h1=new HybridCar(0,30, 120, "This is my car");
		h1.printCarInfo();
		h1.startEngine();
		h1.drive();

		ElectricCar e1=new ElectricCar(150,30, "This is my car");
		e1.printCarInfo();
		e1.startEngine();
		e1.drive();
		
		GasPoweredCar g1=new GasPoweredCar(150,30, "This is my car");
		g1.printCarInfo();
		g1.startEngine();
		g1.drive();
	}

}
