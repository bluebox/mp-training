import java.util.Scanner;

public class TaskMain {
	 public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
	
		while(true) {
		System.out.println("enter a number : ");
		String n=sc.next();
		try {
			Long num=Long.parseLong(n);
			if(num%9==0 && num>0)
				System.out.println("sum of digits till the sum becomes less than 10 : 9");
			else if(num==0){
				System.out.println("sum of digits till the sum becomes less than 10 : 0");
			}
			else if(num<0){
				System.out.println("the given number is negative number ");
			}
			else {
				System.out.println("sum of digits till the sum becomes less than 10 : "+num%9);

			}
		}
		catch(NumberFormatException nfe) {
			System.out.println("Invalid input");
			continue;
		}
		
	}
	 } 
}


