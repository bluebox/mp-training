package inheritance.shapes;

public class Main {

	public static void main(String[] args) {
		
		Circle circle = new Circle(3.75);
		System.out.println(circle.getArea());
		Cylinder cylinder = new Cylinder(5, 13);
		System.out.println(cylinder.ghtVolume());

	}

}
