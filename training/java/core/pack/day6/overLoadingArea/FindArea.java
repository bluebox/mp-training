package day6.overLoadingArea;

public class FindArea {
 public static double area(double d1,double d2) {
	 
	 if(d1<=0 || d2<=0)
		 return -1;
	 return d1*d2;
 }
 
 public static double area(double d1) {
	 return d1<=0? -1:d1*d1;
 }
}
