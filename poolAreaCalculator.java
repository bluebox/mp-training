package Day3;

public class poolAreaCalculator {

	public static void main(String[] args) {
		
     Rectangle rect=new Rectangle(10,20);
     System.out.println(rect.getArea());
     Cuboid cube=new Cuboid(10,20,10);
     System.out.println(cube.getVolume()+" "+cube.getArea())	;	
		
	}

}

class Rectangle{
 private double width;
	private double length;
	public Rectangle(double width, double length) {
		if(width>=0)this.width = width;
		if(length>=0)this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	
	public double getArea() {
		return width*length;
	}
	
	
	
}

class Cuboid extends Rectangle{

	private double height;
	
	public Cuboid(double width, double length,double height) {
		super(width, length);
		if(height>=0) {
			this.height=height;
		}
	}

	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		return this.getArea()*this.getHeight();
	}
	
}
