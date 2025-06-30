package Day2;

public class AreaOfCircle {

	public static void main(String[] args) {
		double radius=-5.0;
		areaOfCircle(radius);
		double x=1.0;
		double y=2.0;
		System.out.println("the area of rectangle is "+areaOfRectangle(x,y));
		
	
	}
	public static void areaOfCircle(double radius) {
		if(radius>0) {
		double pi=3.14;
		System.out.println("the area of circle is " + pi*(radius*radius) );
		}
		if(radius<=0) {
			System.out.println("invalid value");
		}
	}
	public static double areaOfRectangle (double x,double y) {
		if((x<=0)||(y<=0)){
			return -1;
		}
		return x*y;	
	}
	 

}
