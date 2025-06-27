package Day2_26_06;

public class PrimeRange {
	public static void main(String args[]) {
		int count=0;
		int low=0,high=50;
		for(int i=low;i<high;i++) {
			if(isPrime(i)) {
				count++;
				System.out.println(i+" is Prime");
			}
		}
		System.out.println("Total count of prime numbers in the range "+low+" and "+high+" is "+count);
	}
	public static boolean isPrime(int num) {
		if(num<2) {
			return false;
		}
		if(num==2) {
			return true;
		}
		for(int i=2;i<=num/2;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}
}
