package Day2_26_06;

public class EqualChecker {
	public static void main(String args[]) {
		System.out.println(Checker(-1,-1,-1));
		System.out.println(Checker(9,9,9));
		System.out.println(Checker(15,36,15));
		System.out.println(Checker(1,2,3));
	}
	public static String Checker(int num1,int num2,int num3) {
		if(num1<0 || num2 <0 || num3 <0) {
			return "Invalid input";
		}
		if(num1==num2 && num2 == num3) {
			return "All numbers are Equal";
		}
		if(num1==num2 || num2 == num3 || num1==num3) {
			return "Neither all are equal nor different";
		}
		return "All numbers are different";
	}
}
