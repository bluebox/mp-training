package Interfaces;

public class Main {

	public static void main(String[] args) {
		
		Bike bike =new Bike();
		bike.changeGare(1);
		System.out.println(bike);
		bike.speed(2);
		System.out.println(bike);
		bike.applyBreaks(1);
		
		System.out.println(bike);
		
		Car car =new Car();
		car.changeGare(2);
		System.out.println(car);

		car.speed(4);
		System.out.println(car);

		car.applyBreaks(1);
		
		System.out.println(car);
		

	}

}
