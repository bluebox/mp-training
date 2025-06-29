

class Rectangle
{

double length,width;
	
	Rectangle(double length,double width)
	{
		if(width<0)
			this.width=0;
		else
			this.width=width;
		
		if(length<0)
			this.length=0;
		else
			this.length=length;
	}
	
	
	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}

	double getArea() {
		return length*width;
	}
}

class Cuboid extends Rectangle
{
	double height;
	Cuboid(double width,double length,double height)
	{
		super(length,width);
		if(height<0)
			this.height=0;
		else
			this.height=height;
	}
	
	double getheight()
	{
		return height;
	}
	double getVolume()
	{
		return getArea()*height;
	}
}
public class CuboidArea {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cuboid c=new Cuboid(4,5,7);
		System.out.println("Height of the Cuboid "+c.getheight());
		System.out.println("Width of the Cuboid "+c.getWidth());
		System.out.println("Length of the Cuboid "+c.getLength());
		
		
		System.out.println("voulme of the cuboid "+ c.getVolume());

	}

}
