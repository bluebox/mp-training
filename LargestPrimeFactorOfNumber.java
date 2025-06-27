
public class LargestPrimeFactorOfNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("Largest Prime of 21 is "+getLargestPrime(21));
			System.out.println("Largest Prime of 217 is "+getLargestPrime(217));
			System.out.println("Largest Prime of 0 is "+ (getLargestPrime(0) <0) != null? "Invalid Value":getLargestPrime(0));
	}
    
	public static int getLargestPrime(int n) {
		int max=0;
		if(n<1) {
			return -1;
		}
		else
		{
			for(int i=1;i<n;i++) {
				if(n%i == 0) {
					if(max<i) {
						max=i;
					}
				}
			}
			return max;
		}
	}
	
}
