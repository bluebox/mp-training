package day7.hasSameLastDigit;
import java.util.*;

public class HasSameLastDigit {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2,t3;
		System.out.println("Enter the three numbers enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			t3=sc.nextInt();
			
			if(!CheckDigit.isValid(t1)||!CheckDigit.isValid(t1)|| !CheckDigit.isValid(t1)) {
				System.out.println("invalid range ,select numbers between 10-1000");
				System.out.println("---------------------");
				continue;
			}
			System.out.println("The 3 numbers having same last digit "+CheckDigit.hasSameDigit(t1,t2,t3));
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
