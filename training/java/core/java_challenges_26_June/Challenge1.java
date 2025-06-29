
public class Challenge1 {

	public static double area(double radius)
	{
		if(radius<0)
			return -1;
		else
			return 3.14*radius*radius;
	}
	
	public static double area(double x, double y)
	{
	           return x*y;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(area(3.00));
		System.out.println(area(4,5));
		System.out.println(area(-1));
		System.out.println(area(5.0));

	}

}
