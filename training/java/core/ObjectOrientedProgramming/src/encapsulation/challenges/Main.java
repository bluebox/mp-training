package encapsulation.challenges;

public class Main {
	
	public static void main(String[] args) {
		Car santro = new ElectricCar("Hyndai",100, 400000);
		santro.startEngine();
		santro.drive();
		
		Car nexon = new GasPoweredCar("Tata", 1000, 3);
		nexon.startEngine();
		nexon.drive();
		
		Car tiago = new HybridCar("Tata", 10000, 30000, 2);
		tiago.startEngine();
		tiago.drive();
	}

}
