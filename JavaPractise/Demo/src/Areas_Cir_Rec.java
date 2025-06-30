import java.util.Scanner;

public class Areas_Cir_Rec {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the radius value:");
		double radius = sc.nextDouble();
		System.out.println("The Area of Circle is : "+area(radius));
		System.out.println("Enter the length and Breadth values:");
		double length=sc.nextDouble();
		double breadth=sc.nextDouble();
		System.out.println("The Area of Rectangle is : "+ area(length,breadth));

	}
	public static double area(double r) {
		if(r<0) return -1;
		return 3.14*r*r;
		
	}
	public static double area(double x,double y) {
		if(x<0 || y<0) return -1;
		return x*y;
		
	}

}
