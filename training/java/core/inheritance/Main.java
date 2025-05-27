
public class Main {

	public static void main(String[] args) {
		Circle circle=new Circle(5.25);
		Cylinder cylinder =new Cylinder(4.55,5.6);
		
		System.out.println("Radius of Circle is :"+circle.getRadius());
		System.out.println("Area of Circle is :"+circle.getArea(circle.getRadius()));
		System.out.println("\nRadius of Cylinder is :"+cylinder.getRadius());
		System.out.println("Height of Cylinder is :"+cylinder.getHeight());
		System.out.println("Volume of Cylinder is :"+cylinder.getVolume());
		
	}

}
