
public class Main {

	public static void main(String[] args) {
		Circle circle=new Circle(3.75);
		System.out.println("circle.radius= "+circle.getRadius());
		System.out.println("circlr.area= "+circle.getArea());
		
			Cylinder cylinder=new Cylinder(5.87, 4.55);
			System.out.println("cylinder radius= "+cylinder.getRadius());
			System.out.println("cylinder.height="+cylinder.geHeight());
			System.out.println("cylinder.area="+cylinder.getArea());
			System.out.println("cylinder.volume="+cylinder.getVolume());
	}

}
