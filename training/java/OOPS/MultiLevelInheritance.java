
public class MultiLevelInheritance {

	public static void main(String[] args) {
		

		Car c= new Car(4,3333,"audi");
		Audi b= new Audi(2,3453,"honda");
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
class Audi extends Car
{

	public Audi(int wheels, int number, String brand) {
		super(wheels, number, brand);
		
	}
	
}
