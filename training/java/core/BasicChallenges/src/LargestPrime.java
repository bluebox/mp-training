
public class LargestPrime {
	public static void main(String[] args) {
		System.out.println(largestPrime(34));
		System.out.println(largestPrime(-34));

		System.out.println(largestPrime(0));

		System.out.println(largestPrime(343454676));


	}

	public static int largestPrime(int n) {
		if(n<=0)
		{
			return -1;
			
		}
		int ans = 1;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if(n%i==0)
			{
				return n/i;
			}

		}
		return 1;
	}

}
