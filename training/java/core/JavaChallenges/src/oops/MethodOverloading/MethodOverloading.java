package oops.MethodOverloading;

public class MethodOverloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int square=area(2);
		System.out.println("Area of Square is:"+ square);
		int rectangle=area(4,2);
		System.out.println("Area of Rectangle is:"+rectangle);
		}
		public static int area(int side)
		{
			return side*side;
		}
		public static int area(int length,int breadth)
		{
			return length*breadth;
		}

}
