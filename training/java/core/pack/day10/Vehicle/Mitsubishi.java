package day10.Vehicle;

public class Mitsubishi extends Car {
	Mitsubishi(int cylinders,String name){
		super( cylinders, name);
	}
	@Override
	public String startEngine() {
		return getName()+"'s engine is starting";
	}
	@Override
	public String accelerate() {
		return this.getName()+" is accelerating";
	}
	@Override
	public String brake() {
		return getName()+" is breaking";
	}
}
