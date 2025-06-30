package Day2;

public class CountOfPrime {

	public static void main(String[] args) {
		int count=0;
		for(int i=10;i<=30;i++) {
			if(isPrime(i)) {
				System.out.println("number "+i+" is a prime number");
				count++;
				if(count==3) {
					System.out.println("got three prime numbers are");
					break;
				}
			}
		}
	}
		public static boolean isPrime(int i) {
			if(i<=2) {
				return (i==2);
			}
			for(int div=2;div<=i/2;div++) {
				if(i%div==0) {
					return false;
				}
			}
			return true;
		}
		
			

}
