
public class Abstraction {
	public static void main(String [] args)
	{
		Square c=new Square(22.3);
		
		System.out.println("the area is "+c.getArea());
	}

}
class Square{
	private double length;

	
	public Square(double length) {
		super();
		this.length = length;
	}
	
	public double getArea()
	{
		return (length * length);
	}
	
}
