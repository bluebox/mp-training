import java.util.*;

public class MethodOverLoading {
	public static double convertToCentimeters(int inches) {
		return 2.54d*inches;
	}
	public static double convertToCentimeters(int feets, int inches) {
		return ((12*feets)+inches)*2.54d;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner (System.in);
		int inches=sc.nextInt();
		int feets=sc.nextInt();
		double inchesToCentimeters=convertToCentimeters(inches);
		double feetsAndInchesToCentimeters=convertToCentimeters(feets, inches);
		System.out.println(inches+" Inches = "+inchesToCentimeters+" CM");
		System.out.println(feets+" Ft and "+inches+" Inches = "+feetsAndInchesToCentimeters+" CM");
		
	}

}
