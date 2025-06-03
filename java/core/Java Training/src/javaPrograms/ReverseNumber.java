package javaPrograms;
import java.util.*;

public class ReverseNumber {
	
	public static void reverseNumber(int n) {
		int temp=n;
		if(n<0) {
			n=-1*n;
		}
		int rev=0,rem;
		while(n>0) {
			rem=n%10;
			rev=rev*10+rem;
			n/=10;
		}
		if(temp<0) {
			System.out.println("The "+temp+" reversed number form is "+-1*rev);
		}
		else {
			System.out.println("The "+temp+" reversed number form is "+rev);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=sc.nextInt();
		reverseNumber(n);
		sc.close();
	}

}
