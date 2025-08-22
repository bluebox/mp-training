
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car[] cars= {
				new GasPoweredCar("Tayota",12.5,4),
				new ElectricCar("Tesla",45.0,75),
				new HybridCar("Hyundai",25.0,4,50)	
		};
		for(Car c:cars)
		{
			c.startEngine();
			c.Drive();
			c.runEngine();
		}
	}

}

