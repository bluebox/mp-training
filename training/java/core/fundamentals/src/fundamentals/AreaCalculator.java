package fundamentals;
class CalculateArea{
	public double area(double radius)
	{ 
		if(radius < 0)
		{
			return -1;
		}
		return 3.141*radius*radius;
	}
	public double area(double length,double breadth)
	{
		if(length < 0 || breadth < 0)
		{
			return -1;
		}
		return length*breadth;
	}
}
public class AreaCalculator {
	public static void main(String args[])
	{
		CalculateArea calciA=new CalculateArea();
		System.out.println(calciA.area(5.0));
		System.out.println(calciA.area(-1.0));
		System.out.println(calciA.area(5.0,4.0));
		System.out.println(calciA.area(-1.0,4.0));
	}
}
