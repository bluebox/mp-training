package training_java.May27;

public class GasPoweredCar extends Car{
	double avgKmPerLitre;
	int cylinders;
	public void startEngine() {
		System.out.println("GasPoweredCar");
	}
	public void drive() {
		System.out.println("petrol drive");
	}
	protected void runEngine() {
		super.runEngine();
		System.out.println("Petrol Engine");
		
	}
	public static void main(String[] args) {
		Car c=new GasPoweredCar();
		System.out.println(c.getClass().getSimpleName());
		c.runEngine();
		
	}
	

}
