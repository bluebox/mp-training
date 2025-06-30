import java.util.Scanner;

public class DigitSum_Challenge {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Eneter the number to calculate the som of didgits:");
		int number=sc.nextInt();
		int ans = sumDigits(number);
		if(ans == -1) {
			System.out.println("Invalid Input");
		}
		else {
			System.out.println("The Digits sum is :"+ ans);
		}

	}
	public static int sumDigits(int num) {
		if(num<0) {
			return -1;
		}
		if(num<9) {
			return num;
		}
		int sum=0;
		while(num!=0) {
			int digit=num%10;
			sum+=digit;
			num=num/10;
		}
		return sum;
	}

}
