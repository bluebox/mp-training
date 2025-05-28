package samplecodes;

public class DecimalComparator {
	public static void main(String[] args) {
		System.out.println(areEqualByThreeDecimalPlaces(-1.3232,-1.3239));
	}
	public static boolean areEqualByThreeDecimalPlaces(double a,double b) {
		String s1=Double.toString(a);
		String s2=Double.toString(b);
		if(s1.charAt(0)=='-') {
			return s1.substring(0, 5).equals(s2.substring(0,5));
		}
		return s1.substring(0, 4).equals(s2.substring(0,4));
	}
}
