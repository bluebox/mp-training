package javaPrograms;
import java.util.*;
public class PerfectNumber {
	public static boolean isPerfectNumber(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			if(n%i==0) {
				sum+=i;
			}
		}
		if(sum==n) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Eneter the number : ");
		int n=sc.nextInt();
		if(isPerfectNumber(n)) {
			System.out.println("The number "+n+" is Perfect number");
		}
		else{
			System.out.println("The number "+n+" is not Perfect number");
		}
		sc.close();
	}

}
