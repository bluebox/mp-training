package samplecodes;

public class LargestPrimeFactor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int res=getLargestPrimeFactor(21);
		System.out.println("Largest Prime Facor is "+res);
	}
	public static int getLargestPrimeFactor(int n) {
		if(n<=0) return -1;
		int largestPrimeFactor=0;
		for(int i=1;i<=n;i++) {
			if(n%i==0 && isPrime(i)) {
				largestPrimeFactor=i;
			}
		}
		return largestPrimeFactor;
	}
	public static boolean isPrime(int n) {
		for(int i=2;i<n;i++) {
			if(n%i==0) return false;
		}
		return true;
	}

}
