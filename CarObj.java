
public class CarObj {
	public static void main(String[] args) {
		Car car = new Car();
		car.setMake("porsche");
		car.setModel("Carrera");
		car.setDoors(2);
		car.setConvertible(true);
		car.setColor("black");
		System.out.println("make = " + car.getMake());
		System.out.println("model = " + car.getModel());
		System.out.println(car);
	}
}
