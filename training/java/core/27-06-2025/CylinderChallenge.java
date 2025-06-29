import java.util.*;
class Circle
{
	double radius;
	Circle(double radius)
	{
		if(radius<0)
			this.radius=0;
		else
			this.radius=radius;
		 
	}
	
	double getRadius()
	{
	return radius;	
	}
	double getArea()
	{
		return (Math.PI*radius*radius);
	}
}

class Cylinder extends Circle{
	double height;
	Cylinder(double radius,double height)
	{
		 super(radius);
		 if(height<0)
			 this.height=0;
		 else
			 this.height=height;
	}
	public double getHeight() {
		return height;
	}
	public double getVolume()
	{
		return getArea()*height;
	}
	
	
	
}
public class CylinderChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cylinder c=new Cylinder(10,8);
		System.out.println("The area of the circle is :"+c.getArea());
		System.out.println("the Voulme of the cylinder is :"+c.getVolume());

	}

}
