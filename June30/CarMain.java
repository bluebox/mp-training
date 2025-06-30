package June30;

public class CarMain {
	public static void main(String[] args) {
		Car[] cars= {
				new GasPoweredCar("Tayota",12.5,4),
				new ElectricCar("Tesla",45.0,75),
				new HybridCar("Hyundai",25.0,4,50),
				new Car("Audi")
		};
		for(Car c:cars)
		{
			c.startEngine();
			c.drive();
			c.runEngine();
		}
	}
}
