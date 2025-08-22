
public class CalculateArea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double res1=Area(20.00);
		if(res1==-1)
		{
			System.out.println("invalid radius");
		}
		else
		{
		System.out.println(res1);
		}
		double res2=Area(20.00,10.00);
		if(res2==-1)
		{
			System.out.println("invalid length or width");
		}
		else
		{
		System.out.println(res2);
		}
		
	}
	public static double Area(double radius)
	{
		if(radius<0)
		{
			return -1;
		}
		else
		{
			return 3.14*radius*radius;
		}
			
	}
	public static double Area(double length,double width)
	{
		if(length<0||width<0)
		{
			return -1;
		}
		else
		{
			return length*width;
		}
			
	}

}
