package Hierachical;

public class Inheritance {
	public static void main(String[] args) {
	

		Car c= new Car(4,3333,"audi");
		Bike b= new Bike(2,3453,"honda");
		System.out.println(c);
		System.out.println(b);
	}
}
class vehicle
{
	 private int Wheels;
	 private int number;
	 private String brand;
	public vehicle(int wheels, int number, String brand) {
		super();
		Wheels = wheels;
		this.number = number;
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "number is:"+number+" brand is:"+brand+ " wheels are:"+Wheels;
	}
	 
}
class Car extends vehicle
{

	
	public Car(int wheels, int number, String brand) {
		super(wheels, number, brand);
	}
	
	
}
class Bike extends vehicle
{

	public Bike(int wheels, int number, String brand) {
		super(wheels, number, brand);
		
	}
	
}

