package oops.Inheritance;
class Rectangle
{
	int length;
	int breadth;
	Rectangle(int length,int breadth)
	{
		this.length=length;
		this.breadth=breadth;
	}
	public double getArea()
	{
		return length*breadth;
	}
	
}
class Cuboid extends Rectangle
{
	int height;
	Cuboid(int length,int breadth,int height)
	{
		super(length,breadth);
		this.height=height;
	}
	public double getArea()
	{
		return super.getArea()*height;
	}
	
}

public class RectangleandCuboid {
	public static void main(String []args)
	{
		Rectangle c=new Cuboid(6,3,2);
		System.out.println(c.getArea());
	}
}
