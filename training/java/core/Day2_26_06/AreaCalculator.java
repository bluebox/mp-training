package Day2_26_06;

public class AreaCalculator {
	public static void main(String args[]) {
		System.out.println(area(15.2));
		System.out.println(area(3,5.6));
	}
	public static double area(double side) {
		if(side<0) {
			return -1;
		}
		return 22d/7d*side*side;
	}
	public static double area(double side1,double side2) {
		if(side1<0 || side2<0) {
			return -1;
		}
		return side1*side2;
	}
}
