package june26_methods;
import java.util.Scanner;
public class getEvenDigitsSum {
	
	public static int getEvenDigitsSumm(int num) {
		if(num<0)
			return -1;
		int sum=0;
		int rev=0;
		int evensum=0;
		while(num>0) {
			int rem=num%10;
			if(rem%2==0)
				sum+=rem;
			rev=rev*10+rem;
			num/=10;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number: ");
		int num=sc.nextInt();
		System.out.println("Sum of Even Digits in number : "+getEvenDigitsSumm(num));
		sc.close();

	}

}
