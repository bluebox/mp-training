package training_java.May26;



public class Cylinder extends Circle{
	double height;
	public Cylinder(double radius,double height) {
		super(radius);
		if(height<0) {
			this.height=0;
		}
		else {
			this.height=height;
		}	
		
	}
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return super.getArea()*height;
	}

public static void main(String[] args) {
	Cylinder c=new Cylinder(4.5,7.8);
	System.out.println(c.getHeight());
	System.out.println(c.getVolume());
	
	
}
}
