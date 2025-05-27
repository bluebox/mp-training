package CylinderClassWithCircleInheritanceChallenge;

public class Main {
	public static void main(String [] args)
	{
		Circle c1 = new Circle(3.75);
		System.out.println("Area of the circle: " + c1.getArea());
		Cylinder cyn1 = new Cylinder(c1.getRadius(), 5.0);
		System.out.println("Volume of the cylinder: " + cyn1.getVolume());
	}
}
