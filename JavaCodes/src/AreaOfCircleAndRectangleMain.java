import java.util.*;
public class AreaOfCircleAndRectangleMain {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the Radius of Circle");
		double radius=sc.nextDouble();
		CircleArea circle=new CircleArea(radius);
		System.out.println("radius of circle="+circle.getRadius());
		System.out.println("area of cirlce="+circle.getArea());
		Cyclinder cyc=new Cyclinder(radius,20);
		System.out.println(cyc.getVolume());
		Rectangle rec=new Rectangle(5,20);
		System.out.println(rec.getArea());
	}

}
