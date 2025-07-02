
public class CuboidPoolArea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r1=new Rectangle(5,10);
		System.out.println("rectangle width= "+r1.getWidth());
		System.out.println("rectangle length= "+r1.getLength());
		System.out.println("rectangle area= "+r1.getArea());
		Cuboid c2=new Cuboid(5,10,5);
		System.out.println("Cuboid width= "+c2.getWidth());
		System.out.println("cuboid length= "+c2.getLength());
		System.out.println("Cuboid height= "+c2.getHeight());
		System.out.println("Cuboid area= "+c2.getArea());
		System.out.println("Cuboid Volume= "+c2.getVolume());

	}

}
class Rectangle
{
	private double width;
	private double length;
	Rectangle(double width,double length)
	{
		 this.width  = (width  < 0) ? 0 : width;
		   this.length = (length < 0) ? 0 : length;
	}
	public double getWidth()
	{
		return width;
	}
	public double getLength()
	{
		return length;
	}
	public double getArea()
	{
		return width*length;
	}
}
class Cuboid extends Rectangle
{
	private double height;
	Cuboid(double width,double length,double height)
	{
		super(width,length);
		this.height = (height < 0) ? 0 : height;
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
