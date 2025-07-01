package Interfaces;

public class Bike implements Vehicle {
	int gare;
	int speed;

	@Override
	public void changeGare(int gare) {
		// TODO Auto-generated method stub
		this.gare=gare;
		
	}

	@Override
	public void speed(int speed) {
		// TODO Auto-generated method stub
		this.speed=this.speed+speed;
	}

	@Override
	public void applyBreaks(int breaks) {
		// TODO Auto-generated method stub
		this.speed=this.speed-breaks;
	}

	@Override
	public String toString() {
		return "Bike [gare=" + gare + ", speed=" + speed + "]";
	}
	
	
	

}
