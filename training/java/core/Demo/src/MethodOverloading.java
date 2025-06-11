package ExpressionsStatementsMethodOverloading;

public class MethodOverloading {
	
	public static void main(String[] args) {
		System.out.println(area(5,10));
		System.out.println(area(3));
		
	}
	
	public static double area(double s1, double s2) {
		return s1*s2;
	}
	
	public static double area(double r) {
		return Math.PI*r*r;
	}

}
