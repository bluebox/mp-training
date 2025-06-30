package com.tulasidhar.june30.CarClassChallenge;

public class GasPoweredCar extends Car{
	double avgKmPerLitre;
	int cylinders;
	
	@Override
	public void startEngine() {
		System.out.println("Started car with keys and combustion started in the engine!");
	}
	
	@Override
	public void drive() {
		runEngine();
	}
	
	@Override
	protected void runEngine() {
		System.out.println("Used Accelerator and exhaust is making sweet sound and the car is moving!");
	}
	
}
