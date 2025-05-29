package day6.teenNumbers;
import java.util.*;

public class TeenNumbers {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		TeenCheck l=new TeenCheck();
		int inp1,inp2,inp3,inp4;
		System.out.println("Enter the trio numbers or -1 to quit");
		try {
			while(true) {
				inp1=sc.nextInt();
				if(inp1==-1)
					break;
				inp2=sc.nextInt();
				inp3=sc.nextInt();
				
				System.out.println("inputs has the teen number "+l.hasTeen(inp1,inp2,inp3)+" **");
				
				System.out.println("enter the input to check if teen or not");
				inp4=sc.nextInt();
				System.out.println("input entered is teen "+l.isTeen(inp4));
				System.out.println("---------------------");
			}
		}
		catch(Exception e) {
			System.out.println("Exception raised try again with valid inputs ");
		}
	
	}
}
