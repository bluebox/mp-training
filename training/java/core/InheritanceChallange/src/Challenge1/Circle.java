package Challenge1;

public class Circle {
	Double radius;
	

	public Circle(Double radius) {
		
		this.radius = radius;
		if(radius<0)
		{
			radius=0d;
		}
		
	}

	public Double getRadius() {
		return radius;
	}
	public Double getArea()
	{
		return Math.PI*radius*radius;
	}
	
	
	

}
