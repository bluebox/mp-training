package day7.canpack;

import java.util.Scanner;

public class CanPack {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2,t3;
		System.out.println("Enter no.of bags and goal enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			t3=sc.nextInt();
			
			System.out.println(FlourPack.canPack(t1,t2,t3));
			System.out.println("----------------------------");
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
