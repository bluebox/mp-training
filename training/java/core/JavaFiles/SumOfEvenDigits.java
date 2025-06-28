import java.util.Scanner;

public class SumOfEvenDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a Number: ");
		int num=sc.nextInt();
		
		int sum=0;
		
		while(num>0) {
			sum=sum+ ((num%10)%2==0 ? num%10 :0 );
			num=num/10;
		}
		System.out.println("Sum of the Even digits numbers: "+sum);
		
	}

}
