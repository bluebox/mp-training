
public class AreaCalculator {
	private static double pi=3.14159265359;
	double area(double x) {
		if(x<0) {
			return -1;
		}
		return pi*x*x;
	}
	int area(int x,int y) {
		if((x<0)||(y<0)) {
			return -1;
		}
		return x*y;
	}
	double area(double x,double y) {
		if((x<0)||(y<0)) {
			return -1;
		}
		return x*y;
	}
	public static void main(String arg[]) {
		AreaCalculator a=new AreaCalculator();
		System.out.println(a.area(5));
		System.out.println(a.area(-5));
		System.out.println(a.area(5,-3));
		System.out.println(a.area(5,7));
		System.out.println(a.area(5.837,7.6574));
	}
}
