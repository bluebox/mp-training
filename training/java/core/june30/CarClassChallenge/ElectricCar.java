package com.tulasidhar.june30.CarClassChallenge;

public class ElectricCar extends Car{
	double avgKmsPerCharge;
	int batterySize;
	
	@Override
	public void startEngine() {
		System.out.println("Started car with keys and motor is ready to work!");
	}
	
	@Override
	public void drive() {
		runEngine();
	}
	
	@Override
	protected void runEngine() {
		System.out.println("Used Accelarator the motor is working to make the car move");
	}
	
}
