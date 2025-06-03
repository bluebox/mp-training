package oops.circleRedi;

public class Circle {
	protected double redius;
	public Circle(double redius) {
		this.redius=redius;
	}
	
	public double getRedius() {
		return redius;
	}
	
	public void circleArea() {
		double cirArea=(3.14)*redius*redius;
		System.out.println("Circle Area : "+String.format( "%.2f", cirArea));
	}
}
