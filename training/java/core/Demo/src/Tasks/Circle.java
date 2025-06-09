package Tasks;

public class Circle {
	
	public double radius;
	
	public Circle(double radius) {
		if(radius<0) this.radius=0;
		else this.radius=radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		double r=radius;
		return Math.PI*r*r;
	}

}
