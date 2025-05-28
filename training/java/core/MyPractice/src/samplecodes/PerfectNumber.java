package samplecodes;

public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPerfectNumber(28));
	}
	public static boolean isPerfectNumber(int n) {
		int sum=0;
		for(int i=1;i<n;i++) {
			if(n%i==0) {
				sum+=i;
			}
		}
		return sum==n;
	}

}
