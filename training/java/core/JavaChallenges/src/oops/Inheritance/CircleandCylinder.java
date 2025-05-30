package oops.Inheritance;
class Circle
{
	double radius;
	Circle(double radius)
	{
		this.radius=radius<0?0:radius;
	}
	public double getArea()
	{
		return 3.14*radius*radius;
	}
	
}
class Cylinder extends Circle
{
	int height;
	Cylinder(double radius,int height)
	{
		super(radius);
		this.height=height;
	}
	public double getArea()
	{
		return super.getArea()*height;
	}
	
}

public class CircleandCylinder {
	public static void main(String []args)
	{
		Cylinder c=new Cylinder(3,6);
		System.out.println(c.getArea());
	}
}
