
public class CircleInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1=new Circle(3.75);
		System.out.println("circle radius= "+c1.getRadius());
		System.out.println("circle.area= "+c1.getArea());
		Cylinder c2=new Cylinder(5.55,7.25);
		System.out.println("Cylinder radius= "+c2.getRadius());
		System.out.println("Cylinder height= "+c2.getHeight());
		System.out.println("Cylinder area= "+c2.getArea());
		System.out.println("Cylinder Volume "+c2.getVolume());

	}

}
class Circle
{
	double radius;
	Circle(double radius)
	{
		this.radius = (radius < 0) ? 0 : radius;
	}
	public double getRadius()
	{
		return radius;
	}
	public double getArea()
	{
		return Math.PI*radius*radius;
	}
}
class Cylinder extends Circle
{
	double height;
	Cylinder(double radius,double height) {
		super(radius);
		this.height = (height < 0) ? 0 :height;
	}
	public double getHeight()
	{
		return height;
	}
	public double getVolume()
	{
		return getArea()*height;
	}
	
}

