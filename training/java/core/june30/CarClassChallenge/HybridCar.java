package com.tulasidhar.june30.CarClassChallenge;

public class HybridCar extends Car{
	double avgKmPerLitre;
	int batterySize;
	int cylinders;
	
	@Override
	public void startEngine() {
		System.out.println("Started car with keys and selected the source of fuel!");
	}
	
	@Override
	public void drive() {
		runEngine();
	}
	
	@Override
	protected void runEngine() {
		System.out.println("Used Accelarator the motor or the engine is working to make the car move!");
	}
}
