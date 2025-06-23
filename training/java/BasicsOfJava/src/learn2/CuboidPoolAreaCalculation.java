package learn2;

public class CuboidPoolAreaCalculation {
	
	public static void main(String[] args) {
		
		Rectangle rectangle = new Rectangle(7.0,8.0);
		
		System.out.println("width is " + rectangle.getWidth());
		System.out.println("length is " + rectangle.getLength());
		System.out.println("Area is " + rectangle.getArea());
		
		
		Cuboid cuboid = new Cuboid(10.0,12.0,12.0);
		
		System.out.println("width of cylinder is " + cuboid.getWidth());
		System.out.println("length of cylinder is " + cuboid.getLength());
		System.out.println("height of cylinder is " + cuboid.getHeight());
		System.out.println("volume of cylinder is " + cuboid.getVolume());
		System.out.println("area of base is " + cuboid.getArea());
		
	}
}

class Rectangle{
	
	private double width;
	private double length;
	
	public Rectangle(double width,double length) {
		if(width < 0) {
			this.width = 0;
		}
		if(length < 0) {
			this.length = 0;
		}
		
		if((width >= 0) && (length>=0)) {
			this.length = length;
			this.width = width;
		}
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getArea() {
		return width * length;
	}
	
}



class Cuboid extends Rectangle{
	
	private double height;
	public Cuboid(double width,double length, double height) {
		super(width,length);
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		return this.getArea() * height;
	}
}