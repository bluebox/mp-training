import java.util.Scanner;

public class LargestPrimeFactor {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
		int num=sc.nextInt();
		for(int i=num;i>=1;i--) {
			if(num%i==0) {
				if(isPrime(i)) {
					System.out.println("The largest prife factor is :"+i);
					break;
				}
			}
		}
		
	}
	public static boolean isPrime(int num) {
		for(int i=2;i<num;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}

}
