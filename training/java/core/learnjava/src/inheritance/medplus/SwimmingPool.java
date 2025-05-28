package inheritance.medplus;

public class SwimmingPool {
	
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(23,0);
		System.out.println("Length of Rectangel ; "+rectangle.getLength());
		System.out.println("Width of Rectangle : "+rectangle.getWidth());
		System.out.println("Area of Rectangle : "+rectangle.getArea());
		
		Cuboid c = new Cuboid(5,6,7);
		System.out.println("Length of Cuboid ; "+c.getLength());
		System.out.println("Width of Cuboid : "+c.getWidth());
		System.out.println("Heigth of Cuboid : "+c.getHeight());
		System.out.println("Volume of Cuboid : "+c.getVolume());
		
		
	}

}
class Rectangle{
	private double length;
	private double width;
	
	public Rectangle(double length , double width) {
		if (length < 0) this.length = 0;
		else this.length = length;
		
		if (width <0) this.width =0;
		else this.width = width;
	}
	
	public double getLength() {
		return length;
	}
	public double getWidth() {
		return width;
	}
	public double getArea() {
		return width*length;
	}
	
}

class Cuboid extends Rectangle{
	
	private double height ;
	public Cuboid(double length,double width,double heigth) {
		super(length,width);
		if (heigth <0 ) this.height =0;
		else this.height = heigth;
	}
	
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return this.getArea()*height;
	}
}