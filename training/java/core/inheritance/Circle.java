
public class Circle {
	private double radius;
	public double getRadius() {
		return this.radius;
	}
	public double getArea(double radius) {
		return Math.PI*radius*radius;
	}
	public Circle(double radius) {
		
		this.radius=(radius<0)?0:radius;
	}
}
