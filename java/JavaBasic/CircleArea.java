package logical;

public class CircleArea {
	public static void main(String [] args) {
		double radius = 5.0d;
		double area = calculateArea(radius);
		System.out.println("the Area is : " + area);
	}
	public static double calculateArea(double radius) {
		return radius*radius*(double)(22.0d/7.0d);
	}
}
