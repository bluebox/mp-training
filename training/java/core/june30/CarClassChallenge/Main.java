package com.tulasidhar.june30.CarClassChallenge;

public class Main {
	public static void main(String[] args) {
		Car car = new ElectricCar();
		Car car1 = new GasPoweredCar();
		Car car2 = new HybridCar();
		
		car.startEngine();
		car1.startEngine();
		car2.startEngine();
		
		System.out.println("The runtime object time of second car is " + car1.getClass().getName());
	}
}
