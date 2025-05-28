package calculateVolumeInheritance;

public class cuboid extends Rectangle{
	double height;

	public cuboid(double width,double length,double height) {
		super(width,length);
		this.height=height;
	}

	public double getHeight() {
		return height;
	}
	public double getVolume()
	{
		return (super.getArea() * this.height);
	}
	public static void main(String [] args)
	{
		cuboid c=new cuboid(22.3,54,34);
		System.out.println(c.getHeight());
		System.out.println(c.getLength());
		System.out.println(c.getWidth());
		System.out.println(c.getArea());
		System.out.println(c.getVolume());
	}
}
