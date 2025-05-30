package methods;

import java.util.Scanner;

public class DecimalComparator {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Two Double Value's:");
		double a=sc.nextDouble();
		double b=sc.nextDouble();
        
        System.out.println(isEqualUptoThreeDecimalPlaces(a,b));      
    }
	public static boolean isEqualUptoThreeDecimalPlaces(double a, double b) {
        int aInt = (int) (a * 1000);
        int bInt = (int) (b * 1000);
        return aInt == bInt;
    }

}