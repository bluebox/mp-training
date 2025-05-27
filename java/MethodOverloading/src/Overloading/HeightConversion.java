package Overloading;

public class HeightConversion {
	public static void main(String [] args) {
		int inches= 67;
		int feet=5;
		int inch=7;
		double centimeters=convertToCentimeters(inches);
		System.out.println(inches + " inches = "+ centimeters +" centimeters"  );
		centimeters=convertToCentimeters(feet,inch);
		System.out.println(feet + "feet and " + inch + " inches = "+ centimeters +" centimeters"  );
	}
public static double convertToCentimeters(int inches) {
	return (double)(inches * 2.54d);
}
public static double convertToCentimeters(int feet,int inches) {
	int resultInches = (feet * 12 + inches);
	return convertToCentimeters(resultInches);
}
}
