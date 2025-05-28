import java.util.Scanner;

public class DigitSum {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=x;
		int sum=0;
		while(x!=0) {
			sum+=(x%10);
			x/=10;
		}
		System.out.println("Sum of digits in "+y+" is "+sum);
	}
}
