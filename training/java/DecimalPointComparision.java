import java.util.Scanner;

public class DecimalPointComparision {
	public static boolean areEqualByThreeDecimalPlaces(double num1, double num2) {
		return (long)num1*1000==(long)num2*1000;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		double num1=sc.nextDouble();
		double num2=sc.nextDouble();
		boolean result=areEqualByThreeDecimalPlaces(num1,num2);
		System.out.print(result);
	}

}
