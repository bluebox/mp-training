package CylindeWithCircleInheritance;

public class CylinderWithCircleMain {
public static void main(String []args)
 {
	Circle c1=new Circle(3.75);
	System.out.println( "radius :"+c1.getRadius());
	System.out.println( "Area :"+c1.getArea());
	Cylinder cndr1=new Cylinder(5.55,7.25);
	System.out.println( "radius :"+cndr1.getRadius());
	System.out.println( "cylinder area :"+cndr1.getArea());
	System.out.println( "volume :"+cndr1.getVolume());
	
  }
}
