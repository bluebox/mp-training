package oops;
class Engine{
	String type;

	public Engine(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Engine [type=" + type + "]";
	}
}
class Car{
	String model;
	Engine engine;
	public Car(String model, Engine engine) {
		super();
		this.model = model;
		this.engine = engine;
	}
	public void display() {
		System.out.println( "Car [model=" + model + ", " + engine.toString() + "]");
	}
	
}
public class Composition {

	public static void main(String[] args) {
		Engine engine = new Engine("V6");
		Car car = new Car("Honda", engine);
		car.display();
	}

}
