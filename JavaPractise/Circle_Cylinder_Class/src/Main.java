
public class Main {

	public static void main(String[] args) {
		Circle cir =new Circle(3.75);
		System.out.println(cir.getRadius());
		System.out.println(cir.getArea());
		
		Cylinder cyl =new Cylinder(5.5,7.5);
		System.out.println(cyl.getRadius());
		System.out.println(cyl.getHeight());
		System.out.println(cyl.getArea());
		System.out.println(cyl.getVolume());
	}

}
