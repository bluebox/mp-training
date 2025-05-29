package samplecodes;

public class CuboidPoolAreaCalculation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r=new Rectangle(5.2,6.3);
		System.out.println("Rectangle's Length and Width are "+r.getLength() +" and "+r.getWidth()+"respectively");
		System.out.println("Area of the rectangle is "+r.getArea());
		
		
		Cuboid c=new Cuboid(4.3,9.6,2.8);
		System.out.println("Cuboid length = "+ c.getLength());
		System.out.println("Cuboid Width = "+ c.getWidth());
		System.out.println("Cuboid Height = "+ c.getHeight());
		System.out.println("Area of the Cuboid = "+ c.getArea());

	}

}
class Rectangle{
	private double width;
	private double length;
	Rectangle(double width,double length){
		if(width<0)this.width=0;
		else this.width=width;
		if(length<0) this.length=0;
		else this.length=length;
	}
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public double getArea() {
		return length*width;
	}
}
class Cuboid extends Rectangle{
	private double height;
	Cuboid(double width, double length,double height) {
		super(width, length);
		this.height=height;
		// TODO Auto-generated constructor stub
	}
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return getArea()*height;
	}
	
}
