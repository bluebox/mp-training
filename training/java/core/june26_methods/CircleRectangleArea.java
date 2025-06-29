package june26_methods;
import java.util.Scanner;
public class CircleRectangleArea {

	public static double area(double r) {
		return 3.14*r*r;
		
	}
	
	public static double area(double l,double b) {
		return l*b;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the shape you want to calculate Area");
		String shape=sc.next();
		switch(shape) {
		case "circle" -> {System.out.println("Enter the radius of Circle");
						double radius=sc.nextDouble();
						System.out.println("Area of Circle : "+area(radius));}
		case "rectangle" -> {System.out.println("Enter the lenth and breadth o rectangle");
							double l=sc.nextDouble();
							double b=sc.nextDouble();
							System.out.println("Area of Rectangle : "+area(l,b));}
		default -> System.out.println("Invalid shape");
						
		}
		sc.close();
	}

}
