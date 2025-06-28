import java.util.Scanner;

public class SumOfDigits {
	public static void main(String args[]) {
		
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter a number: ");
		int num= sc.nextInt(),temp=num;
		
		int sum=0;
		
		while(num>0) {
			sum=sum+num%10;
			num=num/10;
		}
		
		System.out.println("Sum of given number "+temp+" is "+sum);
	}

}
