package javaPrograms;
import java.util.*;
public class Factors {
	
	public static void printFactors(int n) {
		for(int i=1;i<=n;i++) {
			if(n%i==0) {
				System.out.print(i+" ");
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=sc.nextInt();
		System.out.println("Factors of "+n+" are :");
		printFactors(n);
		sc.close();
	}

}
