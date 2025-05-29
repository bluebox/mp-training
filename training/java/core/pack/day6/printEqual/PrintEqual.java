package day6.printEqual;
import java.util.*;

public class PrintEqual {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1,t2,t3;
		System.out.println("Enter three inputs and -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			t3=sc.nextInt();
		IsEqual.isEquals(t1,t2,t3);
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
