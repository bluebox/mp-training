package june26_Loops;
import java.util.Scanner;
public class DigitSum {
	
	public static int sumOfDigits(int number) {
		if (number<0) 
			return -1;
		
		int sum=0;
		while (number>0) {
			int rem=number%10;
			sum+=rem;
			number/=10;
		}
		return sum;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number that you want to perform sum of digits:");
		int num=sc.nextInt();
		int result=sumOfDigits(num);
		if (result!=-1)
				System.out.println("Sum of Digits of given number: "+result);
		else
			System.out.println("Invalue value was passed");
	
	}

}
