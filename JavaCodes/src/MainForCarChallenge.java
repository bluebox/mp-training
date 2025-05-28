
public class MainForCarChallenge {

	public static void main(String[] args) {
		 ElectricCar car=new ElectricCar();
		 car.drive();
		 car.startEngine();
		 HybridCar c=new HybridCar();
		 c.drive();
		 c.startEngine();
		 c.runEngine();
		 GasPoweredCar d=new GasPoweredCar();
		 d.drive();
		 d.startEngine();
		 d.runEngine();
	}

}
