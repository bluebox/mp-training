package Challenge1;

public class Cylinder extends Circle{
	double height;

	public Cylinder(Double radius, double height) {
		super(radius);
		this.height = height;
		if(height<0)
		{
			height=0;
		}
	}

	public double getHeight() {
		return height;
	}
	public double getVolume()
	{
		return height*super.getArea();		
	}
	

}
