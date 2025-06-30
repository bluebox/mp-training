package Day3_27_06;

public class Cylinder extends Circle{
	private double height;

	public Cylinder(double radius, double height) {
		super(radius);
		if(height<0) {
			this.height=0;
		}else {
		this.height = height;
		}
	}

	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return super.getArea()*this.getHeight();
	}
	public static void main(String args[]) {
		Cylinder c1=new Cylinder(5.0,6.0);
		System.out.println(c1.getVolume());
	}
	
}
