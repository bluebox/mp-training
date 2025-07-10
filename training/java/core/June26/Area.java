package June26;

public class Area {
	public static void main(String[] args) {
		System.out.println(calculateArea(5.0));
		System.out.println(calculateArea(-1));
		System.out.println(calculateArea(5.0, 4.0));
		System.out.println(calculateArea(-1.0, 4.0));
	}
	public static double calculateArea(double radius) {
		if(radius < 0) return -1.0;
		return 3.14159*radius*radius;
	}
	public static double calculateArea(double x, double y) {
		if(x < 0 || y < 0) return -1.0;
		return x*y;
	}
}
