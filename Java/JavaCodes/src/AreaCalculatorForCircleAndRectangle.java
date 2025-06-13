import java.util.*;
public class AreaCalculatorForCircleAndRectangle {
    public static double areaOfCirle(double radius) {
    	if(radius<0)return -1;
    	return 3.14*radius;
    }
    public static double areaOfRectangle(double length,double breadth) {
    	if(length<0 || breadth<0)return -1;
    	return length*breadth;
    }
	public static void main(String[] args) {
		System.out.println("enter the radius of circle");
		Scanner sc=new Scanner(System.in);
		double radius=sc.nextDouble();
		System.out.println("enter the length and breadth of a rectangle");
		double length=sc.nextDouble();
		double breadth=sc.nextDouble();
		System.out.println(areaOfCirle(radius));
		System.out.print(areaOfRectangle(length,breadth));
	}

}
