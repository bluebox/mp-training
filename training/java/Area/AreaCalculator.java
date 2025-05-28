package Area;

public class AreaCalculator {

	public static void main(String[] args) {
		double radius=32.24,length=23.44,breadth=34.45;
		System.out.println("the area of the circle with radius "+radius+" is"+ calculateArea(radius));
		System.out.println("the area of the rectangle with length "+length+ " and breadth "+breadth+ " is "+ calculateArea(length,breadth));	
	}
	public static double calculateArea(double radius)
	{
		if(radius<0)
			return (double) -1;
		return 3.14*radius*radius;
	}
	public static double calculateArea(double length,double breadth)
	{
		if(length<0 || breadth<0)
			return (double) -1;
		return length*breadth;
	}


}
