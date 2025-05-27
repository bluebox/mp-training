package training_java.May23;

public class Area {
public static double  area(double radius) {
	if(radius<0) {
		return -1.0d;
	}
	return 3.14*radius*radius;
}
public  static double area(double x,double y) {
	if(x<0 || y<0) {
		return -1;
	}
	return x*y;
}
public static void main(String[] args) {
	System.out.println(area(5.0));
	System.out.println(area(-1));
	System.out.println(area(5.0,4.0));
	System.out.println(area(-1.0,5.0));
}
}
