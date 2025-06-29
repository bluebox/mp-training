package june26_Loops;
import java.util.Scanner;
public class PrimeNumbersCount {
	//if sum of divisors=number then it can be called as perfect number....
	public static boolean isPrime(int num) {
		if(num<=0 || num==1)
			return false;
		for(int i=2;i<=num/2;i+=1) {
			if(num%i==0)
				return false;
			
		}
		return true;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the range of numbers:");
		int start=sc.nextInt();
		int end=sc.nextInt();
		int count_primes=0;
		if(end>1000)
			System.out.println("Enter the range of the numbers that are less than or equal to 1000 ");
		else {
			for(int i=start;i<=end;i++) {
				if (count_primes>3)
					break;
				if(isPrime(i)) {
					System.out.println(i+" is a prime Number");
					count_primes+=1;
				}
				
			}
			
		}
		sc.close();
			
	}

}
