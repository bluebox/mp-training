package corejava.june26_methods;

import java.util.Scanner;

public class SharedDigit {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any two numbers to check whether there is a shared digit between them or not");
		System.out.println("Enter first number");
		int num1=sc.nextInt();
		System.out.println("Enter Second number");
		int num2=sc.nextInt();
		if(hasSharedDigit(num1,num2)) {
			System.out.println("Numbers has shared digit between them");
		}
		else {
			System.out.println("Numbers does not has shared digit between them");
		}
		
		sc.close();
	}
	public static boolean hasSharedDigit(int n1,int n2) {
		String s1=Integer.toString(n1);
		String s2=Integer.toString(n2);
		if(n1>=10 && n2<=99) {
			if(s2.contains(String.valueOf(s1.charAt(0)))|| s2.contains(String.valueOf(s1.charAt(1)))) {
				return true;
			}
		}
		return false;
	}

}
