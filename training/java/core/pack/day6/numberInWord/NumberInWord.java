package day6.numberInWord;
import java.util.*;

public class NumberInWord {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t;
		System.out.println("Enter the number, press -1 to quit");
	try{
		while(true) {
			t=sc.nextInt();
			if(t==-1)
				break;
			
			NumberWord.printNumberInWord(t);
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs "+e);
	}
	}
}
